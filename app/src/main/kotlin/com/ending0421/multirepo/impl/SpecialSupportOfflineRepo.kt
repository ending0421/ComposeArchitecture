package com.ending0421.multirepo.impl

import com.ending0421.multirepo.base.BaseOfflineRepo
import com.ending0421.multirepo.base.ErrorType
import com.ending0421.multirepo.base.MResult
import com.ending0421.multirepo.data.BannerApiData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class SpecificSupportOfflineRepo(coroutineScope: CoroutineScope) : BaseOfflineRepo(coroutineScope) {

    fun getSpecificData(): Flow<MResult<BannerApiData>> {
        return safeApiCall { readDataFromDBForOffline() }
    }

    override fun convert2BusinessSpecificError(httpException: HttpException): ErrorType? {
        TODO("Not yet implemented")
    }

    private fun readDataFromDBForOffline(): BannerApiData {
        return BannerApiData()
    }
}
