package com.ending0421.multirepo.base

sealed class MResult<out T> {
    data class Success<out T>(val data: T) : MResult<T>()
    data class Error<out T>(val error: ErrorType) : MResult<T>()
    data object Loading : MResult<Nothing>()
}