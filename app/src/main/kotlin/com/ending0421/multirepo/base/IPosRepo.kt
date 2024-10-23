package com.ending0421.multirepo.base

import kotlinx.coroutines.flow.Flow

interface IPosRepo {
    fun <T> safeApiCall(apiCall: suspend () -> T): Flow<MResult<T>>
    fun <T> networkApiCall(apiCall: suspend () -> T): Flow<MResult<T>>
}

interface IOfflinePosRepo : IPosRepo {
    fun <T> offlineApiCall(apiCall: suspend () -> T): Flow<MResult<T>>
}