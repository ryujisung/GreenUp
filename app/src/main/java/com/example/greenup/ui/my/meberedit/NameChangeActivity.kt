package com.example.greenup.ui.my.meberedit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityLoginBinding
import com.example.greenup.databinding.ActivityMemberEditMainBinding
import com.example.greenup.databinding.ActivityNameChangeBinding
import com.example.greenup.databinding.ActivityNoti2Binding
import com.example.greenup.ui.my.MyMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class NameChangeActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_name_change)
        val  email =  auth.currentUser?.email
        val binding = DataBindingUtil.setContentView<ActivityNameChangeBinding>(this,
            R.layout.activity_name_change
        )
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MyMainActivity::class.java)
            startActivity(intent)
        }
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

        binding.next.setOnClickListener {
            val name = binding.nameEdtName.text.toString()
            val data = hashMapOf(
                "nickname" to name
            )

            db.collection(email.toString())
                .document("usernickname")
                .set(data)
                .addOnSuccessListener {
                    var intent = Intent(this, MemberEditMainActivity::class.java)
                    startActivity(intent)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "변경에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}