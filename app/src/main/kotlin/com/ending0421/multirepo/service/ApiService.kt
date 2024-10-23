package com.ending0421.multirepo.service

import com.ending0421.multirepo.data.BannerApiData
import retrofit2.http.GET

interface ApiService {
    @GET("https://www.wanandroid.com/banner/jsona")
    suspend fun getSomeData(): BannerApiData
}