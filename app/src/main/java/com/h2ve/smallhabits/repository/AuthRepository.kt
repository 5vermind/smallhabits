package com.h2ve.smallhabits.repository

import com.h2ve.smallhabits.model.LoginResponse
import com.h2ve.smallhabits.model.RegisterResponse
import com.h2ve.smallhabits.network.ApiService
import com.h2ve.smallhabits.util.NetworkUtils
import kotlinx.coroutines.*
import org.koin.dsl.module
import retrofit2.Response


val authModule = module {
    factory { AuthRepository(get(), get()) }
}

class AuthRepository(
    private val api: ApiService,
    private val sharedPreferences: MySharedPreferences,
    private val job: CompletableJob = SupervisorJob(),
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + job),
) {

    suspend fun createUser(userId: String, password: String, passwordAgain: String, name: String): ResultWrapper<RegisterResponse> {
        val fetchRegisterResponse: Deferred<Response<RegisterResponse>> = scope.async {
            api.postAuthRegister(userId, password, passwordAgain, name)
        }

        return try{
            val registerResponse = fetchRegisterResponse.await()
            if (registerResponse.isSuccessful){
                ResultWrapper.Success(registerResponse.body()!!)
            }else{
                ResultWrapper.GenericError(NetworkUtils.getErrorResponse(registerResponse.errorBody()!!))
            }
        } catch (e: Throwable){
            ResultWrapper.NetworkError
        }
    }

    suspend fun login(userId: String, password: String): ResultWrapper<LoginResponse> {
        val fetchLoginResponse: Deferred<Response<LoginResponse>> = scope.async {
            api.postAuthLogin(userId, password)
        }

        return try {
            val loginResponse = fetchLoginResponse.await()
            if(loginResponse.isSuccessful){
                loginResponse.body()?.token?.let { sharedPreferences.setToken(loginResponse.body()!!.token) }
                ResultWrapper.Success(loginResponse.body()!!)
            } else{
                ResultWrapper.GenericError(NetworkUtils.getErrorResponse(loginResponse.errorBody()!!))
            }
        } catch (e: Throwable){
            ResultWrapper.NetworkError
        }
    }

    fun isLogin(): Boolean {
        return sharedPreferences.getToken() != ""
    }
}