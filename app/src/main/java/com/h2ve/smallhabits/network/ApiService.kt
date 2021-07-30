package com.h2ve.smallhabits.network

import com.h2ve.smallhabits.model.LoginRes
import com.h2ve.smallhabits.model.LogoutRes
import com.h2ve.smallhabits.model.RegisterRes
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.CookieManager

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun reqAuthLogin(
        @Field("userId") userId:String,
        @Field("password") password:String
    ): Response<LoginRes>

//    @GET("user/logout")
//    fun reqLogout(): Call<LogoutRes>
//
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun reqAuthRegister(
            @Field("userId") userId:String,
            @Field("password") password:String,
            @Field("passwordAgain") passwordAgain:String,
            @Field("name") name: String
    ): Response<RegisterRes>
//

    companion object {
        private const val BASE_URL = "https://small-habit.herokuapp.com/"

        fun create(): ApiService {
            val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

            val headerInterceptor = Interceptor { //TODO token
                val request = it.request()
                    .newBuilder()
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .cookieJar(JavaNetCookieJar(CookieManager()))
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}