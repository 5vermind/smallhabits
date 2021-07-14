package com.h2ve.smallhabits.view

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.h2ve.smallhabits.R
import com.h2ve.smallhabits.databinding.ActivityMainBinding
import com.h2ve.smallhabits.util.BaseViewBindingActivity
import kotlin.system.exitProcess

class MainActivity: BaseViewBindingActivity<ActivityMainBinding>(bindingFactory = { ActivityMainBinding.inflate(it)}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initNavigation()
    }

    private fun initNavigation() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        // 위와 같은 2번째 방법
        //        NavigationUI.setupWithNavController(
        //            main_bottom_navigation,
        //            findNavController(R.id.main_nav_host)
        //        )
    }

    private fun initView() {
//        binding.appbar.ivAppbarSettings.setOnClickListener {
//            Toast.makeText(this, "go to settings...", Toast.LENGTH_SHORT).show()
//        }
    }

    private var time: Long = 0
    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        val navController = navHostFragment.navController
        navController.currentDestination
        if (navController.currentDestination?.id == R.id.boardFragment) {
            if (System.currentTimeMillis() - time >= 1000) {
                time = System.currentTimeMillis()
                Toast.makeText(this, "뒤로 가기 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
            } else if (System.currentTimeMillis() - time < 1000) {
                finishAffinity()
                System.runFinalization()
                exitProcess(0)
            }
        } else {
            super.onBackPressed()
        }
    }

}
