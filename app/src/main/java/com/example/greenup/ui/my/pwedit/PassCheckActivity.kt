package com.example.greenup.ui.my.pwedit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityNoti2Binding
import com.example.greenup.databinding.ActivityPassCheckBinding
import com.example.greenup.databinding.ActivityPassEditMainBinding
import com.example.greenup.ui.main.MainActivity
import com.example.greenup.ui.my.MyMainActivity
import com.google.firebase.auth.FirebaseAuth

class PassCheckActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_check)
        val binding = DataBindingUtil.setContentView<ActivityPassCheckBinding>(this,
            R.layout.activity_pass_check
        )
        binding.mainImgPrevious.setOnClickListener {
            var intent = Intent(this, MyMainActivity::class.java)
            startActivity(intent)
        }
        val email = user?.email
        binding.next.setOnClickListener {
            val pass = binding.nameEdtName.text
            auth.signInWithEmailAndPassword(
                email.toString(),
                pass.toString()
            ).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val loginintent = Intent(this, PassEditMainActivity::class.java)
                    startActivity(loginintent)
                } else {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                }
            }
        }



    }
}