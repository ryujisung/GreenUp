package com.example.greenup.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.greenup.R
import com.example.greenup.databinding.ActivityLoginBinding
import com.example.greenup.databinding.ActivityMyMainBinding
import com.example.greenup.ui.main.MainActivity
import com.example.greenup.ui.signup.SignUpActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auto = getSharedPreferences("autoskip", MODE_PRIVATE)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this,
            R.layout.activity_login
        )
        val autoSkipEdit = auto.edit()
        autoSkipEdit.putString("autoskip", "True")
        autoSkipEdit.commit()
        auth = FirebaseAuth.getInstance()
        val emailInput = findViewById<EditText>(R.id.login_et_id)
        val passwordInput = findViewById<EditText>(R.id.login_et_pass)
        findViewById<Button>(R.id.login_btn_loginbtn).setOnClickListener {
            auth.signInWithEmailAndPassword(
                emailInput.text.toString(),
                passwordInput.text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val loginintent = Intent(this, MainActivity::class.java)
                        startActivity(loginintent)
                    } else {
                        Toast.makeText(this, "실패!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        findViewById<TextView>(R.id.login_txt_signup).setOnClickListener{
            val loginintent = Intent(this, SignUpActivity::class.java)
            startActivity(loginintent)

        }
        binding.loginCheckPass.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.loginEtPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            } else {
                binding.loginEtPass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }


    }
    override fun onBackPressed() {
        // 뒤로가기 버튼을 무시하고 아무 동작도 수행하지 않음
    }

}