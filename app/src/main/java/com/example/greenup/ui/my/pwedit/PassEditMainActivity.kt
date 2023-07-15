package com.example.greenup.ui.my.pwedit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityNameChangeBinding
import com.example.greenup.databinding.ActivityPassEditMainBinding
import com.example.greenup.ui.main.MainActivity
import com.example.greenup.ui.my.MyMainActivity
import com.example.greenup.ui.my.meberedit.MemberEditMainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class PassEditMainActivity : AppCompatActivity() {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pass_edit_main)
        val binding = DataBindingUtil.setContentView<ActivityPassEditMainBinding>(this,
            R.layout.activity_pass_edit_main
        )



        binding.next.setOnClickListener {
            val newPassword = binding.nameEdtName.text.toString()
            user?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "변경 성공!", Toast.LENGTH_SHORT).show()
                        var intent = Intent(this, MyMainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "비밀번호 변경에 실패하였습니다.", Toast.LENGTH_SHORT).show()
                        val exception = task.exception
                        if (exception != null) {
                            // 실패한 이유를 로그로 출력하거나 사용자에게 알릴 수 있습니다.
                            Log.e("PasswordChange", "비밀번호 변경 실패: ${exception.message}")
                        }
                    }
                }
            binding.loginCheckPass.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.nameEdtName.transformationMethod = HideReturnsTransformationMethod.getInstance()
                } else {
                    binding.nameEdtName.transformationMethod = PasswordTransformationMethod.getInstance()
                }
            }
        }



    }

}