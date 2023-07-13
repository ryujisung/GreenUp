package com.example.greenup.ui.main

import android.os.Bundle
import com.example.greenup.R
import com.example.greenup.databinding.ActivityMainBinding
import com.example.portplay.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var bnv_main = binding.bnvMain

        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_main_home -> {
//                        // 다른 프래그먼트 화면으로 이동하는 기능
//                        val searchFragment = searchFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.fl_con, searchFragment).commit()
                    }
//                    R.id.menu_main_teamBuilding -> {
//                        val settingFragment = FileFragment()
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.fl_con, settingFragment).commit()
//                    }
                }
                true
            }
            selectedItemId = R.id.menu_main_home
        }
    }
}