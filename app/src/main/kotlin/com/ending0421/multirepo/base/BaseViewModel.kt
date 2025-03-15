package com.ending0421.multirepo.base

import androidx.lifecycle.ViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<E : BaseRepoError> : ViewModel() {

    private val _commonError = MutableStateFlow<CommonRepoError?>(null)
    val commonError: StateFlow<CommonRepoError?> = _commonError.asStateFlow()

    private val _specificError = MutableStateFlow<E?>(null)
    val specificError: StateFlow<E?> = _specificError.asStateFlow()

    @Suppress("UNCHECKED_CAST")
    fun handleError(error: BaseRepoError) {
        if (error is CommonRepoError) {
            _commonError.value = error
        } else {
            _specificError.value = error as E
        }
    }
}