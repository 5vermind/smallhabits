package com.h2ve.smallhabits.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.h2ve.smallhabits.databinding.ActivityBoardBinding
import com.h2ve.smallhabits.util.BaseViewBindingActivity

class BoardActivity: BaseViewBindingActivity<ActivityBoardBinding>(bindingFactory = { ActivityBoardBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.appbar.ivAppbarSettings.setOnClickListener {
            Toast.makeText(this, "go to settings...", Toast.LENGTH_SHORT).show()
        }
    }

}
