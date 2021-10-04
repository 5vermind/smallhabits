package com.h2ve.smallhabits.util

import com.google.gson.Gson
import com.h2ve.smallhabits.model.ErrorResponse
import okhttp3.ResponseBody

object NetworkUtils {
    fun getErrorResponse(errorBody: ResponseBody): ErrorResponse {
        val gson = Gson()
        return gson.fromJson(errorBody.string(), ErrorResponse::class.java)
    }
}