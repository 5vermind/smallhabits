package com.h2ve.smallhabits.network

import com.h2ve.smallhabits.model.LoginRes
import com.h2ve.smallhabits.model.LogoutRes
import com.h2ve.smallhabits.model.SignUpRes
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.net.CookieManager

interface ApiService {

    @FormUrlEncoded
    @POST("user/login")
    fun reqLogin(
        @Field("userId") uid:String,
        @Field("password") upw:String
    ): Call<String>

//    @GET("user/logout")
//    fun reqLogout(): Call<LogoutRes>
//
//    @FormUrlEncoded
//    @POST("user/signup")
//    fun reqSignUp(
//            @Field("uid") uid:String,
//            @Field("upw") upw:String,
//            @Field("nickname") nickname:String
//    ): Call<SignUpRes>
//

    companion object {
        private const val BASE_URL = "small-habit.herokuapp.com/"

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