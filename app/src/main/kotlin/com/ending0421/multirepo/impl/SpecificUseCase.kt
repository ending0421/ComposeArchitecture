package com.ending0421.multirepo.impl

import com.ending0421.multirepo.base.APIResult
import com.ending0421.multirepo.data.BannerApiData
import kotlinx.coroutines.flow.Flow

class SpecificUseCase(private val specificRepo: SpecificRepo) {

    fun execute(): Flow<APIResult<BannerApiData>> {
        return specificRepo.getSpecificData()
    }
}