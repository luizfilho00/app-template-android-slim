package com.facom.facomemfoco.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ApiImage(
        @SerializedName("mini_thumb") val miniThumb: String?,
        @SerializedName("thumb") val thumb: String?,
        @SerializedName("medium") val medium: String?,
        @SerializedName("original") val original: String?
)
