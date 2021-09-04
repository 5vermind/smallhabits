package com.h2ve.smallhabits.repository

import android.util.Log
import com.h2ve.smallhabits.model.HabitResponse
import com.h2ve.smallhabits.network.ApiService
import com.h2ve.smallhabits.util.NetworkUtils
import kotlinx.coroutines.*
import org.koin.dsl.module
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


val habitModule = module {
    factory { HabitRepository(get()) }
}


class HabitRepository(
    private val api: ApiService,
    private val job: CompletableJob = SupervisorJob(),
    private val coroutineContext: CoroutineContext = Dispatchers.IO + job
) {
    suspend fun getAllHabit(): ResultWrapper<List<HabitResponse>>{
        return safeApiCall(coroutineContext) { api.getHabit() }
    }

    suspend fun getOneHabit(habitId: String): ResultWrapper<HabitResponse>{
        return safeApiCall(coroutineContext) {api.getHabitOne(habitId)}
    }
}