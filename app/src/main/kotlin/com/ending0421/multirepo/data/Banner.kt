package com.ending0421.multirepo.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Banner(
    @SerialName("desc") var desc: String? = null,
    @SerialName("id") var id: Int? = null,
    @SerialName("imagePath") var imagePath: String? = null,
    @SerialName("isVisible") var isVisible: Int? = null,
    @SerialName("order") var order: Int? = null,
    @SerialName("title") var title: String? = null,
    @SerialName("type") var type: Int? = null,
    @SerialName("url") var url: String? = null
)