package com.h2ve.smallhabits.repository

import com.h2ve.smallhabits.model.LoginResponse
import com.h2ve.smallhabits.model.RegisterResponse
import com.h2ve.smallhabits.network.ApiService
import kotlinx.coroutines.*
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext


val authModule = module {
    factory { AuthRepository(get(), get()) }
}

class AuthRepository(
    private val api: ApiService,
    private val sharedPreferences: MySharedPreferences,
    private val job: CompletableJob = SupervisorJob(),
    private val coroutineContext: CoroutineContext = Dispatchers.IO + job
) {

    suspend fun createUser(userId: String, password: String, passwordAgain: String, name: String): ResultWrapper<RegisterResponse> {
        return safeApiCall(coroutineContext) { api.postAuthRegister(userId, password, passwordAgain, name) }
    }

    suspend fun login(userId: String, password: String): ResultWrapper<LoginResponse> {
        val response: ResultWrapper<LoginResponse> = safeApiCall(coroutineContext) { api.postAuthLogin(userId, password) }
        when(response){
            is ResultWrapper.Success -> {
                sharedPreferences.setToken(response.value.token)
            }
            else -> {}
        }
        return response
    }

    fun isLogin(): Boolean {
        return sharedPreferences.getToken() != ""
    }
}