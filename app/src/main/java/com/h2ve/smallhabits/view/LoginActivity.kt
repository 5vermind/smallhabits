package com.h2ve.smallhabits.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.h2ve.smallhabits.*
import com.h2ve.smallhabits.databinding.ActivityLoginBinding
import com.h2ve.smallhabits.model.LoginRes
import com.h2ve.smallhabits.repository.SharedPreferencesManager
import com.h2ve.smallhabits.viewmodel.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    var loginRes: LoginRes? = null

    private val vm: AuthViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
        // SharedPreferences 안에 값이 저장되어 있지 않을 때 -> Login
        if(SharedPreferencesManager.getUserNick(this).isBlank()) {
            login(binding, api)
        }
        else { // SharedPreferences 안에 값이 저장되어 있을 때 -> 게시판으로 이동s
            Toast.makeText(this, "${SharedPreferencesManager.getUserNick(this)}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()
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
        }

        binding.skipBtn.setOnClickListener {
            SharedPreferencesManager.setUserId(this, "no")
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
