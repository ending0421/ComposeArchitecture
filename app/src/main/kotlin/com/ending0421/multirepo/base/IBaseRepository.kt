package com.ending0421.multirepo.base

import kotlinx.coroutines.flow.Flow

interface IBaseRepository {
    fun <T> safeApiCall(apiCall: suspend () -> T): Flow<APIResult<T>>
    fun <T> networkApiCall(apiCall: suspend () -> T): Flow<APIResult<T>>
}