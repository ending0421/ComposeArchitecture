package com.ending0421.multirepo.base

sealed class APIResult<out T> {
    data class Success<out T>(val data: T) : APIResult<T>()
    data class Error(val error: BaseRepoError) : APIResult<Nothing>()
    data object Loading : APIResult<Nothing>()

    companion object {
        fun <T> success(value: T): APIResult<T> = Success(value)
        fun failure(error: BaseRepoError): APIResult<Nothing> = Error(error)
        fun loading(): APIResult<Nothing> = Loading
    }

    // 扩展函数：成功时执行
    inline fun onSuccess(action: (T) -> Unit): APIResult<T> {
        if (this is Success) action(data)
        return this
    }

    // 扩展函数：失败时执行
    inline fun onError(action: (BaseRepoError) -> Unit): APIResult<T> {
        if (this is Error) action(error)
        return this
    }

    // 扩展函数：加载时执行
    inline fun onLoading(action: () -> Unit): APIResult<T> {
        if (this is Loading) action()
        return this
    }
}