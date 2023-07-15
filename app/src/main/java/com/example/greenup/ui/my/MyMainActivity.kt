package com.example.greenup.ui.my

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityMyMainBinding
import com.example.greenup.ui.LoginActivity
import com.example.greenup.ui.main.MainActivity
import com.example.greenup.ui.my.meberedit.MemberEditMainActivity
import com.example.greenup.ui.my.noti.Noti2Activity
import com.example.greenup.ui.my.noti.NotiActivity
import com.example.greenup.ui.my.pwedit.PassCheckActivity
import com.example.greenup.ui.my.pwedit.PassEditMainActivity
import com.example.portplay.base.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyMainActivity: BaseActivity<ActivityMyMainBinding>(R.layout.activity_my_main){
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_main)
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.setContentView<ActivityMyMainBinding>(this,
            R.layout.activity_my_main
        )
        binding.email =  auth.currentUser?.email
        val db = FirebaseFirestore.getInstance()
        db.collection(auth.currentUser?.email.toString())
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val data = document.data
                    // 데이터 처리 로직 추가
                    val nickname = data["nickname"] as String
                    Log.d("ddd",nickname)
                    binding.nickname =  nickname
                }

            }
            .addOnFailureListener { exception ->
            }
        binding.version = "현재 버전 0.0.1"

        binding.myTxtCuVersion.setOnClickListener {
            Toast.makeText(this, "버전은 사실 2진법을 이루어집니다!", Toast.LENGTH_SHORT).show()
        }
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.myTxtLogout.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.myTxtNoti.setOnClickListener {
            var intent = Intent(this, NotiActivity::class.java)
            startActivity(intent)
        }
        binding.myTxtEdit.setOnClickListener {
            var intent = Intent(this, MemberEditMainActivity::class.java)
            startActivity(intent)
        }
        binding.myTxtPw.setOnClickListener {
            var intent = Intent(this, PassCheckActivity::class.java)
            startActivity(intent)
        }
        binding.myTxtNoti2.setOnClickListener {
            var intent = Intent(this, Noti2Activity::class.java)
            startActivity(intent)
        }
        binding.myTxtCenter.setOnClickListener {
            val url = "https://pf.kakao.com/_rrhIG" // 채널 URL

            // 외부 브라우저를 통해 링크 열기
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }


    }
    override fun onBackPressed() {
        // 뒤로가기 버튼을 무시하고 아무 동작도 수행하지 않음
    }

}