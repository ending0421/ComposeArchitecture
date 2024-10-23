package com.ending0421.multirepo

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow

fun monitorNetworkStatus(context: Context): MutableStateFlow<Boolean> {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkStatus = MutableStateFlow(checkInitialNetworkStatus(connectivityManager))

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            networkStatus.value = true
        }

        override fun onLost(network: Network) {
            networkStatus.value = false
        }
    }

    val networkRequest =
        NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET).build()

    connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    return networkStatus
}

private fun checkInitialNetworkStatus(connectivityManager: ConnectivityManager): Boolean {
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}