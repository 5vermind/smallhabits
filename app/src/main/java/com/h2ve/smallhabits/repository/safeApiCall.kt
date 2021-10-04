package com.h2ve.smallhabits.repository

import com.google.gson.Gson
import com.h2ve.smallhabits.model.ErrorResponse
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import kotlin.coroutines.CoroutineContext

suspend fun <T> safeApiCall(context: CoroutineContext, apiCall: suspend () -> Response<T>): ResultWrapper<T>{
    return withContext(context){
        try {
            val response = apiCall.invoke()
            if(response.isSuccessful){
                ResultWrapper.Success(response.body()!!)
            }
            else{
                ResultWrapper.GenericError(getErrorResponse(response.errorBody()!!), response.code())
            }
        }
        catch (e: Throwable){
            when(e){
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = e.code()
                    val errorResponse = getErrorResponse(e.response()?.errorBody()!!)
                    ResultWrapper.GenericError(errorResponse, code)
                }
                else -> {
                    ResultWrapper.GenericError(null, null)
                }
            }
        }
    }
}

private fun getErrorResponse(errorBody: ResponseBody): ErrorResponse {
    val gson = Gson()
    return gson.fromJson(errorBody.string(), ErrorResponse::class.java)
}