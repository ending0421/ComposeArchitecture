package com.ending0421.multirepo.di

import com.ending0421.multirepo.impl.SpecificViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { SpecificViewModel(get()) }
}