package com.ending0421.multirepo.di


import com.ending0421.multirepo.impl.SpecificRepo
import com.ending0421.multirepo.impl.SpecificSupportOfflineRepo
import org.koin.core.module.Module
import org.koin.dsl.module

val repositoryModule: Module = module {
    single { SpecificRepo(get()) }
    single { SpecificSupportOfflineRepo(get()) }
}