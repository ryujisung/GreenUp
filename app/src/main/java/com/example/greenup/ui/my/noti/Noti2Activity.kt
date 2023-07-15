package com.example.greenup.ui.my.noti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityNoti2Binding
import com.example.greenup.ui.my.MyMainActivity

class Noti2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noti2)
        val binding = DataBindingUtil.setContentView<ActivityNoti2Binding>(this,
            R.layout.activity_noti2
        )
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MyMainActivity::class.java)
            startActivity(intent)
        }
    }
}