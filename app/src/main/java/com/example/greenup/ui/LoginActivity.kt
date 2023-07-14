package com.example.greenup.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.greenup.R
import com.example.greenup.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val auto = getSharedPreferences("autoskip", MODE_PRIVATE)
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


    }
}