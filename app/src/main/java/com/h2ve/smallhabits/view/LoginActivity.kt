package com.h2ve.smallhabits.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.h2ve.smallhabits.*
import com.h2ve.smallhabits.databinding.ActivityLoginBinding
import com.h2ve.smallhabits.model.LoginResponse
import com.h2ve.smallhabits.model.ViewModelTransferObject
import com.h2ve.smallhabits.repository.MySharedPreferences
import com.h2ve.smallhabits.repository.ResultWrapper
import com.h2ve.smallhabits.viewmodel.AuthViewModel
import com.h2ve.smallhabits.viewmodel.HabitViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    var loginRes: LoginResponse? = null

    private val vm: AuthViewModel by viewModel()
    private val sharedPreferences: MySharedPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
        if(MySharedPreferences.getUserNick(this).isBlank()) {
            login(binding, api)
        }
        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> 게시판으로 이동s
            Toast.makeText(this, "${MySharedPreferences.getUserNick(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        */


        binding.loginBtn.setOnClickListener {
            val userId = binding.id.text.toString()
            val password = binding.pw.text.toString()

            vm.loginUser(userId, password)
        }

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
//            sharedPreferences.clearUser()
        }

        binding.skipBtn.setOnClickListener {
            MySharedPreferences.setUserId(this, "no")
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
//            Toast.makeText(this, "${sharedPreferences.getToken()}", Toast.LENGTH_SHORT).show()
        }

        vm.loginLiveData.observe(this){
            when(vm.loginLiveData.value){
                is ResultWrapper.GenericError -> Toast.makeText(this, "${(vm.loginLiveData.value as ResultWrapper.GenericError).error?.message}", Toast.LENGTH_SHORT).show()
                is ResultWrapper.NetworkError -> Toast.makeText(this, "서버 에러 잠시 후 다시 시도해 주세요", Toast.LENGTH_SHORT).show()
                is ResultWrapper.Success -> {
                    //화면전환
                    Toast.makeText(this, "성공", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
