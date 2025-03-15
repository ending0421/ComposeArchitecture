package com.ending0421.multirepo.di

import com.ending0421.multirepo.impl.SpecificUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCaseModule: Module = module {
    single { SpecificUseCase(get()) }
}