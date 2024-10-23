package com.ending0421.multirepo.impl

import com.ending0421.multirepo.base.BaseRepo
import com.ending0421.multirepo.base.MResult
import com.ending0421.multirepo.data.BannerApiData
import com.ending0421.multirepo.service.ApiService
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class SpecificRepo(private val apiService: ApiService) : BaseRepo() {

    fun getSpecificData(): Flow<MResult<BannerApiData>> {
        return safeApiCall { apiService.getSomeData() }
    }

    override fun convert2BusinessSpecificError(httpException: HttpException): SpecificErrorType {
        return SpecificErrorType.fromCode(httpException.code())
    }
}