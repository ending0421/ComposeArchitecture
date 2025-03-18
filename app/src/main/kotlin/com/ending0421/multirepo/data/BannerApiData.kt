package com.ending0421.multirepo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BannerApiData(
    @SerialName("data") var data: ArrayList<Banner> = arrayListOf(),
    @SerialName("errorCode") var errorCode: Int? = null,
    @SerialName("errorMsg") var errorMsg: String? = null
)