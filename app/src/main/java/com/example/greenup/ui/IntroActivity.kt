package com.example.greenup.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.greenup.R
import com.example.greenup.ui.main.MainActivity
import com.example.greenup.ui.viewpager.ViewPagerActivity
import com.google.firebase.auth.FirebaseAuth

class IntroActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        auth = FirebaseAuth.getInstance()
        val shared = getSharedPreferences("autoskip", MODE_PRIVATE)
        val value = shared.getString("autoskip", "False")
        if(auth.currentUser != null){
            var handler = Handler()
            handler.postDelayed({
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            },1000)

        }
        else if(value=="True"){
            var handler = Handler()
            handler.postDelayed({
                var intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            },1000)
        }
        else {
            var handler = Handler()
            handler.postDelayed({
                var intent = Intent(this, ViewPagerActivity::class.java)
                startActivity(intent)

            }, 1000)
        }
    }
}