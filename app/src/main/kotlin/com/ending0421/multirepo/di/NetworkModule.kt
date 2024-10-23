package com.ending0421.multirepo.di

import com.ending0421.multirepo.service.ApiService
import org.koin.core.module.Module
import retrofit2.Retrofit
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val networkModule: Module = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://your.api.url")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}