package com.ending0421.multirepo.impl

import androidx.lifecycle.viewModelScope
import com.ending0421.multirepo.data.BannerApiData
import com.ending0421.multirepo.base.BaseViewModel
import com.ending0421.multirepo.base.MResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SpecificViewModel(private val specificUseCase: SpecificUseCase) :
    BaseViewModel<SpecificErrorType>() {

    private val _data = MutableStateFlow<MResult<BannerApiData>?>(null)
    val data: StateFlow<MResult<BannerApiData>?> get() = _data

    fun fetchData() {
        viewModelScope.launch {
            specificUseCase.execute().collect { result ->
                when (result) {
                    is MResult.Loading, is MResult.Success -> _data.value = result

                    is MResult.Error -> handleError(result.error)
                }
            }
        }
    }
}