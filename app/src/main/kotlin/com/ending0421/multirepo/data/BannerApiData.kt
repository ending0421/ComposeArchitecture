package com.ending0421.multirepo.data

import com.google.gson.annotations.SerializedName


data class BannerApiData(

    @SerializedName("data") var data: ArrayList<Banner> = arrayListOf(),
    @SerializedName("errorCode") var errorCode: Int? = null,
    @SerializedName("errorMsg") var errorMsg: String? = null

)