package com.ending0421.multirepo.impl

import com.ending0421.multirepo.base.APIResult
import com.ending0421.multirepo.base.BaseRepository
import com.ending0421.multirepo.base.httpCode
import com.ending0421.multirepo.data.BannerApiData
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow

class SpecificRepo : BaseRepository<SpecificRepoError>() {

    fun getSpecificData(): Flow<APIResult<BannerApiData>> {
        val url = "https://www.wanandroid.com/banner/jsona"
        return safePostRequest(url, aiVisionRequest)
    }

    override fun convert2BusinessSpecificError(responseException: ResponseException): SpecificRepoError? {
        return SpecificRepoError.fromCode(responseException.httpCode())
    }
}