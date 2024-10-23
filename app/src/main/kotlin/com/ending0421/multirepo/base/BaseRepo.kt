package com.ending0421.multirepo.base

import android.util.Log
import com.ending0421.multirepo.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

private const val TAG = "BaseRepo"

abstract class BaseRepo : IPosRepo {

    override fun <T> safeApiCall(apiCall: suspend () -> T): Flow<MResult<T>> =
        networkApiCall(apiCall)

    override fun <T> networkApiCall(apiCall: suspend () -> T): Flow<MResult<T>> =
        generateMResultFlow(apiCall)

    protected fun <T> generateMResultFlow(apiCall: suspend () -> T) = flow {
        emit(MResult.Loading)
        val response = apiCall.invoke()
        emit(MResult.Success(response))
    }.catch { exception ->
        val errorResult: ErrorType = when (exception) {
            // TODO:
            is SocketTimeoutException -> TODO()
            is IOException -> CommonErrorType.IOExceptionError
            is HttpException -> {
                val code = exception.code()
                val message = exception.message()
                // 优先判断是否是 CommonError , CommonError 交给 Common View 处理.
                // 否则交给具体的业务层 转换为具体的业务错误
                CommonErrorType.fromCode(code) ?: convert2BusinessSpecificError(exception)
                ?: CommonErrorType.UnhandledBusinessError
            }
            //is another other exceptions here
            else -> CommonErrorType.UnknownExceptionError
        }
        // 如果业务层也没有做出相应的错误类型转换, 则 errorResult 为 CommonErrorType.UnhandledError
        // 则意味着业务层可能漏掉了应该要处理的API 错误.
        // 所以 Debug build 下 直接throw exception 让 App Crash
        // Release build 下 记录一个日志到 Sentry 或任何 APM 系统
        if (errorResult == CommonErrorType.UnhandledBusinessError) {
            if (BuildConfig.DEBUG) {
                throw exception
            } else {
                // Report to sentry or any other APM system
                Log.e(TAG, exception.message.toString())
                exception.printStackTrace()
            }
        }
        exception.printStackTrace()
        emit(MResult.Error(errorResult))
    }

    abstract fun convert2BusinessSpecificError(httpException: HttpException): ErrorType?

}