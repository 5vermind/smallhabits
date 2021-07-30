package com.h2ve.smallhabits.repository

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import com.h2ve.smallhabits.model.LoginRes
import com.h2ve.smallhabits.model.RegisterRes
import com.h2ve.smallhabits.network.ApiService
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


val authModule = module {
    factory { AuthRepository(get()) }
}


class AuthRepository(private val api: ApiService) {

    suspend fun createUser(userId: String, password: String, passwordAgain: String, name: String) = api.reqAuthRegister(userId, password, passwordAgain, name)
    suspend fun getUser(userId: String, password: String) = api.reqAuthLogin(userId, password)


//    fun getUser(context: Context, service: ApiService, userId: String, password: String){
//        service.reqLogin(userId, password).enqueue(object : Callback<LoginRes>{
//            override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
//                val loginRes = response.body()
//                if(response.code() == 200){
//                    if (loginRes != null) {
//                        Toast.makeText(context, "register api\nuserId: " + loginRes.token, Toast.LENGTH_SHORT).show()
//                    }
//                }
//                else{
//                    Toast.makeText(context, "fail", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<LoginRes>, t: Throwable) {
//                val dialog = AlertDialog.Builder(context)
//                dialog.setTitle("register api\nFailed connection")
//                dialog.show()
//            }
//        })
//    }
}