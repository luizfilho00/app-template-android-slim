package com.facom.facomemfoco.data.remote.client

import com.facom.facomemfoco.data.remote.entity.ApiError
import com.google.gson.Gson
import okhttp3.ResponseBody

object ApiErrorsFormatter {
    fun deserialize(responseBody: ResponseBody?): ApiError? {
        return Gson()
                .fromJson(responseBody?.string(), ApiError::class.java)
    }
}
