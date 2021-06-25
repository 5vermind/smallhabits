package com.h2ve.smallhabits.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.h2ve.smallhabits.databinding.ActivityBoardBinding

class BoardActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
