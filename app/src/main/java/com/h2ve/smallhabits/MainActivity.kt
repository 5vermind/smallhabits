package com.h2ve.smallhabits

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            val uid = binding.id.text.toString()
            val upw = binding.pw.text.toString()


        }

        binding.signupBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.skipBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, BoardActivity::class.java)
            startActivity(intent)
        }
    }

}
