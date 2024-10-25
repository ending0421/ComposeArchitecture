package com.ending0421.multirepo.data

import com.google.gson.annotations.SerializedName


data class Banner(

    @SerializedName("desc") var desc: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("imagePath") var imagePath: String? = null,
    @SerializedName("isVisible") var isVisible: Int? = null,
    @SerializedName("order") var order: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("type") var type: Int? = null,
    @SerializedName("url") var url: String? = null

)