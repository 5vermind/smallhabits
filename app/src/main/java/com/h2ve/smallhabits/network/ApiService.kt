package com.h2ve.smallhabits.network

import com.h2ve.smallhabits.model.HabitResponse
import com.h2ve.smallhabits.model.LoginResponse
import com.h2ve.smallhabits.model.RegisterResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun postAuthLogin(
        @Field("userId") userId:String,
        @Field("password") password:String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("auth/register")
    suspend fun postAuthRegister(
            @Field("userId") userId:String,
            @Field("password") password:String,
            @Field("passwordAgain") passwordAgain:String,
            @Field("name") name: String
    ): Response<RegisterResponse>

    @GET("habit")
    suspend fun getHabit(): Response<List<HabitResponse>>

    @GET("habit/{habitId}")
    suspend fun getHabitOne(
        @Path("habitId") habitId: String
    ): Response<HabitResponse>

    @DELETE("habit/{habitId}")
    suspend fun deleteHabitOne(
        @Path("habitId") habitId: String
    ): Response<HabitResponse>

}

