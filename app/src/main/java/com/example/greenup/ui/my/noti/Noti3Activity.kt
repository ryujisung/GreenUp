package com.example.greenup.ui.my.noti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityNoti2Binding
import com.example.greenup.databinding.ActivityNoti3Binding
import com.example.greenup.ui.main.MainActivity
import com.example.greenup.ui.my.MyMainActivity

class Noti3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti3)
        val binding = DataBindingUtil.setContentView<ActivityNoti3Binding>(this,
            R.layout.activity_noti3
        )
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}