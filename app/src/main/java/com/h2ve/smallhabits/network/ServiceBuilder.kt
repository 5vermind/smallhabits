package com.h2ve.smallhabits.network

import com.h2ve.smallhabits.BuildConfig
import com.h2ve.smallhabits.repository.AuthRepository
import com.h2ve.smallhabits.repository.MySharedPreferences
import okhttp3.Interceptor
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager


val networkModule = module {
    single { ServiceBuilder(get()).provideRetrofit(get()) }
    factory { ServiceBuilder(get()).provideOkHttpClient() }
    factory { ServiceBuilder(get()).provideApi(get()) }
}


class ServiceBuilder(
    private val sharedPreferences: MySharedPreferences,
    private val BASE_URL: String = "https://small-habit.herokuapp.com/"
) {

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }


    fun provideOkHttpClient(): OkHttpClient{
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val headerInterceptor = Interceptor {
            val request = it.request()
                .newBuilder()
                .addHeader("Authorization", sharedPreferences.getToken())
                .build()
            return@Interceptor it.proceed(request)
        }

        return OkHttpClient().newBuilder().addInterceptor(headerInterceptor)
            .cookieJar(JavaNetCookieJar(CookieManager())).addInterceptor(httpLoggingInterceptor).build()
    }

    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}