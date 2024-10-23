package com.ending0421.multirepo.base

import com.ending0421.multirepo.monitorNetworkStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.core.context.GlobalContext

abstract class BaseOfflineRepo(coroutineScope: CoroutineScope) : BaseRepo(), IOfflinePosRepo {

    private val networkStatusFlow = monitorNetworkStatus(GlobalContext.get().get())
    private var isNetworkConnected: Boolean = true // 默认情况下认为是网络连接状态

    init {
        coroutineScope.launch {
            networkStatusFlow.collect { status ->
                isNetworkConnected = status
            }
        }
    }

    override fun <T> safeApiCall(apiCall: suspend () -> T): Flow<MResult<T>> {
        return if (isNetworkConnected) {
            networkApiCall(apiCall)
        } else {
            offlineApiCall(apiCall)
        }
    }

    override fun <T> offlineApiCall(apiCall: suspend () -> T): Flow<MResult<T>> {
        return generateMResultFlow(apiCall)
    }
}