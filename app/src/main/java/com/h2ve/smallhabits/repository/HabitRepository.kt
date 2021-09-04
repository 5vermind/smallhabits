package com.h2ve.smallhabits.repository

import android.util.Log
import com.h2ve.smallhabits.model.HabitResponse
import com.h2ve.smallhabits.network.ApiService
import com.h2ve.smallhabits.util.NetworkUtils
import kotlinx.coroutines.*
import org.koin.dsl.module
import retrofit2.Response


val habitModule = module {
    factory { HabitRepository(get()) }
}


class HabitRepository(
    private val api: ApiService,
    private val job: CompletableJob = SupervisorJob(),
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + job)
) {
    suspend fun getAllHabit(): ResultWrapper<List<HabitResponse>>{
        val fetchAllHabitResponse: Deferred<Response<List<HabitResponse>>> = scope.async {
            api.getHabit()
        }

        return try{
            val habitResponse = fetchAllHabitResponse.await()
            if(habitResponse.isSuccessful){
                ResultWrapper.Success(habitResponse.body()!!)
            }
            else{
                ResultWrapper.GenericError(NetworkUtils.getErrorResponse(habitResponse.errorBody()!!))
            }
        } catch (e: Throwable){
            ResultWrapper.NetworkError
        }
    }

    suspend fun getOneHabit(habitId: String): ResultWrapper<HabitResponse>{
        val fetchHabitResponse: Deferred<Response<HabitResponse>> = scope.async {
            api.getHabitOne(habitId)
        }

        return try {
            val habitResponse = fetchHabitResponse.await()
            if(habitResponse.isSuccessful){
                ResultWrapper.Success(habitResponse.body()!!)
            }
            else{
                ResultWrapper.GenericError(NetworkUtils.getErrorResponse((habitResponse.errorBody()!!)))
            }
        } catch (e: Throwable){
            ResultWrapper.NetworkError
        }
    }
}