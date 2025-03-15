package com.ending0421.multirepo.impl

import androidx.lifecycle.viewModelScope
import com.ending0421.multirepo.base.APIResult
import com.ending0421.multirepo.base.BaseViewModel
import com.ending0421.multirepo.data.BannerApiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpecificViewModel(private val specificUseCase: SpecificUseCase) :
    BaseViewModel<SpecificRepoError>() {

    private val _data = MutableStateFlow<APIResult<BannerApiData>?>(null)
    val data: StateFlow<APIResult<BannerApiData>?> get() = _data

    fun fetchData() {
        viewModelScope.launch {
            specificUseCase.execute().collect { result ->
                when (result) {
                    is APIResult.Loading, is APIResult.Success -> _data.value = result

                    is APIResult.Error -> handleError(result.error)
                }
            }
        }
    }
}