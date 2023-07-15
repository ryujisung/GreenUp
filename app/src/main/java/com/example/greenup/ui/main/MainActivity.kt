package com.example.greenup.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityMainBinding
import com.example.greenup.databinding.ActivityMyMainBinding
import com.example.greenup.ui.my.MyMainActivity
import com.example.greenup.ui.my.noti.Noti3Activity
import com.example.portplay.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this,
            R.layout.activity_main
        )
        findViewById<ImageView>(R.id.main_img_my).setOnClickListener {
            var intent = Intent(this, MyMainActivity::class.java)
            startActivity(intent)
        }
        binding.mainImgNoti.setOnClickListener {
            var intent = Intent(this, Noti3Activity::class.java)
            startActivity(intent)
        }
    }
    override fun onBackPressed() {
        // 뒤로가기 버튼을 무시하고 아무 동작도 수행하지 않음
    }

}