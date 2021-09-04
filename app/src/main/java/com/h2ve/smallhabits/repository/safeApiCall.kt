//package com.h2ve.smallhabits.repository
//
//import com.h2ve.smallhabits.model.ErrorResponse
//import com.h2ve.smallhabits.util.NetworkUtils
//import kotlinx.coroutines.CoroutineDispatcher
//import kotlinx.coroutines.withContext
//import org.koin.core.context.GlobalContext
//import retrofit2.HttpException
//import retrofit2.Retrofit
//import java.io.IOException
//import java.lang.Exception
//
//suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T>{
//    return withContext(dispatcher){
//        try {
//            ResultWrapper.Success(apiCall.invoke())
//        }
//        catch (throwable: Throwable){
//            when(throwable){
//                is IOException -> ResultWrapper.NetworkError
//                is HttpException -> {
//                    val code = throwable.code()
//                    val errorResponse = convertErrorBody(throwable)
//                    ResultWrapper.GenericError(code, errorResponse)
//                }
//                else -> {
//                    ResultWrapper.GenericError(null, null)
//                }
//            }
//        }
//    }
//}
//
//fun convertErrorBody(throwable: HttpException): ErrorResponse? {
//    return try {
//        throwable.response()?.errorBody()?.let {
//            val retrofit: Retrofit = GlobalContext.get().koin.get()
//            retrofit.responseBodyConverter<ErrorResponse>(ErrorResponse::class.java, ErrorResponse::class.java.annotations).convert(it)
//        }
//    }catch (exception: Exception){
//        null
//    }
//}
