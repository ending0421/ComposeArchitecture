package com.ending0421.multirepo

import android.app.Application
import com.ending0421.multirepo.di.appModule
import com.ending0421.multirepo.di.networkModule
import com.ending0421.multirepo.di.repositoryModule
import com.ending0421.multirepo.di.useCaseModule
import com.ending0421.multirepo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DemoApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DemoApp)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}