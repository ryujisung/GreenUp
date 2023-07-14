package com.example.greenup.ui.my

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityFoodScanBinding
import com.example.greenup.databinding.ActivityMainBinding
import com.example.greenup.databinding.ActivityMyMainBinding
import com.example.portplay.base.BaseActivity

class MyMainActivity: BaseActivity<ActivityMyMainBinding>(R.layout.activity_my_main){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_main)
        val binding = DataBindingUtil.setContentView<ActivityMyMainBinding>(this,
            R.layout.activity_my_main
        )
        findViewById<TextView>(R.id.my_txt_noti).setOnClickListener {
            var intent = Intent(this, NotiActivity::class.java)
            startActivity(intent)
        }

    }
}