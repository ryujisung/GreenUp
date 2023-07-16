package com.example.greenup.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityMainBinding
import com.example.greenup.ui.base.BaseActivity
import com.example.greenup.ui.my.MyMainActivity
import com.example.greenup.ui.mainnoti.Noti3Activity
import com.example.greenup.ui.moreeco.EcoMoreActivity
import com.example.greenup.ui.moreeco.EcoMoreMoreActivity
import com.example.greenup.ui.morerecycle.ReMoreActivity
import com.example.greenup.ui.morerecycle.ReMoreMoreActivity

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
        binding.ddddd.setOnClickListener {
            var intent = Intent(this, FoodScanActivity::class.java)
            startActivity(intent)

        }
        binding.mainImgQr.setOnClickListener {
            var intent = Intent(this, FoodScanActivity::class.java)
            startActivity(intent)
        }
        binding.mainTxtRecycle.setOnClickListener {
            var intent = Intent(this, ReMoreActivity::class.java)
            startActivity(intent)
        }
        binding.mainTxtEco.setOnClickListener {
            var intent = Intent(this, EcoMoreActivity::class.java)
            startActivity(intent)
        }
        binding.mainImgEco.setOnClickListener {
            var intent = Intent(this, EcoMoreMoreActivity::class.java)
            intent.putExtra("title","‘지구를 위한다는 착각’이라는 착각")
            startActivity(intent)


        }
        binding.mainImgRecycle.setOnClickListener {
            var intent = Intent(this, ReMoreMoreActivity::class.java)
            intent.putExtra("title","귤껍질은 일반쓰레기인가")
            startActivity(intent)
        }
        binding.mainImgEco1.setOnClickListener {
            var intent = Intent(this, EcoMoreMoreActivity::class.java)
            intent.putExtra("title","‘지구를 위한다는 착각’이라는 착각")
            startActivity(intent)


        }
        binding.mainImgRecycle1.setOnClickListener {
            var intent = Intent(this, ReMoreMoreActivity::class.java)
            intent.putExtra("title","귤껍질은 일반쓰레기인가")
            startActivity(intent)
        }
        binding.mainImgEco2.setOnClickListener {
            var intent = Intent(this, EcoMoreMoreActivity::class.java)
            intent.putExtra("title","‘지구를 위한다는 착각’이라는 착각")
            startActivity(intent)


        }
        binding.mainImgRecycle2.setOnClickListener {
            var intent = Intent(this, ReMoreMoreActivity::class.java)
            intent.putExtra("title","귤껍질은 일반쓰레기인가")
            startActivity(intent)
        }

        val main_dt_search = findViewById<EditText>(R.id.main_dt_search)
        main_dt_search.setOnClickListener {
            var intent = Intent(this, FoodSearchActivity::class.java)
            startActivity(intent)
        }

    }
    override fun onBackPressed() {
        // 뒤로가기 버튼을 무시하고 아무 동작도 수행하지 않음
    }

}