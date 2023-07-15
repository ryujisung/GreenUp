package com.example.greenup.ui.my.meberedit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityMemberEditMainBinding
import com.example.greenup.databinding.ActivityNoti2Binding
import com.example.greenup.ui.LoginActivity
import com.example.greenup.ui.my.MyMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MemberEditMainActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member_edit_main)
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.setContentView<ActivityMemberEditMainBinding>(this,
            R.layout.activity_member_edit_main
        )
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MyMainActivity::class.java)
            startActivity(intent)
        }
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

        binding.email1.setOnClickListener {
            Toast.makeText(this@MemberEditMainActivity, "이메일은 변경할 수 없어요", Toast.LENGTH_SHORT).show()
        }
        binding.name.setOnClickListener {
            var intent = Intent(this, NameChangeActivity::class.java)
            startActivity(intent)

        }
        binding.arrow.setOnClickListener {
            var intent = Intent(this, NameChangeActivity::class.java)
            startActivity(intent)

        }

    }
}