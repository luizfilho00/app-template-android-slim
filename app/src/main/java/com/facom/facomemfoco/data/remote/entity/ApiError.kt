package com.facom.facomemfoco.data.remote.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ApiError(
        @SerializedName("message")
        val errorMessage: String?,
        @SerializedName("errors")
        val errors: ArrayList<String>?
) : Serializable
