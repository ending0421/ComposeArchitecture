package com.ending0421.multirepo.impl

import com.ending0421.multirepo.data.BannerApiData
import com.ending0421.multirepo.base.MResult
import kotlinx.coroutines.flow.Flow

class SpecificUseCase(private val specificRepo: SpecificRepo) {

    fun execute(): Flow<MResult<BannerApiData>> {
        return specificRepo.getSpecificData()
        // 可以在这里对数据进行进一步处理
    }
}