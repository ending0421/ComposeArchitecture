package com.ending0421.multirepo.base

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<E : ErrorType> : ViewModel() {

    private val _commonError = MutableStateFlow<CommonErrorType?>(null)
    val commonError: StateFlow<CommonErrorType?> get() = _commonError.asStateFlow()

    private val _specificError = MutableStateFlow<E?>(null)
    val specificError: StateFlow<E?> get() = _specificError.asStateFlow()

    @Suppress("UNCHECKED_CAST")
    fun handleError(error: ErrorType) {
        if (error is CommonErrorType) {
            _commonError.value = error
        } else {
            _specificError.value = error as E
        }
    }
}