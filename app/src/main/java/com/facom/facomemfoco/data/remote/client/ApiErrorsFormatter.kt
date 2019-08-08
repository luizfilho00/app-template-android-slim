package com.facom.facomemfoco.data.remote.client

import com.google.gson.Gson
import okhttp3.ResponseBody

object ApiErrorsFormatter {
    fun deserialize(responseBody: ResponseBody?): com.facom.facomemfoco.data.remote.entity.ApiError? {
        return Gson()
                .fromJson(responseBody?.string(), com.facom.facomemfoco.data.remote.entity.ApiError::class.java)
    }
}
