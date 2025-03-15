package com.ending0421.multirepo.base

import com.ending0421.multirepo.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json
import timber.log.Timber
import java.io.IOException

private const val TAG = "BaseRepository"
val api_key = ""

val json: Json = Json {
    ignoreUnknownKeys = true // 忽略不需要的字段
}

// 初始化 Ktor 客户端，使用 OkHttp 作为引擎
val ktorOkHttpClient = HttpClient(OkHttp) {
    install(ContentNegotiation) {
        json(json)
    }
    install(Logging) {
        level = LogLevel.ALL
    }
}

abstract class BaseRepository<E : BaseRepoError> : IBaseRepository {

    // 统一封装 API 调用并处理错误
    override fun <T> safeApiCall(apiCall: suspend () -> T): Flow<APIResult<T>> =
        networkApiCall(apiCall)

    override fun <T> networkApiCall(apiCall: suspend () -> T): Flow<APIResult<T>> =
        generateAPIResultFlow(apiCall)

    // 处理 API 请求的 Flow 并捕获异常
    private fun <T> generateAPIResultFlow(apiCall: suspend () -> T) = flow {
        emit(APIResult.Loading)
        try {
            val response = apiCall.invoke() // 执行 API 调用
            emit(APIResult.Success(response)) // 成功返回结果
        } catch (exception: Exception) {
            // 根据不同异常类型处理错误
            val errorResult: BaseRepoError = when (exception) {
                // SocketTimeout
                is SocketTimeoutException -> {
                    CommonRepoError.TimeoutExceptionRepoError
                }
                // IOException
                is IOException -> {
                    CommonRepoError.IOExceptionRepoError
                }

                is RedirectResponseException, is ServerResponseException, is ClientRequestException -> {
                    val code = exception.response.status.value
                    CommonRepoError.fromCode(code) ?: convert2BusinessSpecificError(
                        exception
                    ) ?: CommonRepoError.UnhandledBusinessRepoError
                }

                //
                else -> {
                    CommonRepoError.UnknownExceptionRepoError
                }
            }
            handleErrorResult(exception, errorResult)
            emit(APIResult.Error(errorResult))
        }
    }

    // **封装 HTTP POST 请求**，支持泛型 Request & Response
    inline fun <reified Request, reified Response> safePostRequest(
        url: String, requestBody: Request
    ): Flow<APIResult<Response>> = safeApiCall {
        ktorOkHttpClient.post(url) {
            header(HttpHeaders.Authorization, "Bearer $api_key")
            contentType(ContentType.Application.Json)
            setBody(requestBody) // **自动序列化请求体**
        }.body()
    }

    private fun handleErrorResult(exception: Exception, errorResult: BaseRepoError) {
        if (errorResult == CommonRepoError.UnhandledBusinessRepoError) {
            if (BuildConfig.DEBUG) {
                // will throw this exception & crash app in debug build
                throw exception
            } else {
                Timber.tag(TAG).e(exception.message.toString())
                exception.printStackTrace()
            }
        }
    }

    // 具体子类可重写此函数来处理特定业务的错误转换
    abstract fun convert2BusinessSpecificError(responseException: ResponseException): E?
}

// Extension function to get HTTP status code from ResponseException
fun ResponseException.httpCode(): Int {
    return this.response.status.value
}
