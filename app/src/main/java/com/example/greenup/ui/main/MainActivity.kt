package com.example.greenup.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.example.greenup.R
import com.example.greenup.databinding.ActivityMainBinding
import com.example.greenup.ui.my.MyMainActivity
import com.example.portplay.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ImageView>(R.id.main_img_my).setOnClickListener {
            var intent = Intent(this, MyMainActivity::class.java)
            startActivity(intent)
        }
    }
}