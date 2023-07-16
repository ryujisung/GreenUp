package com.example.greenup.ui.moreeco

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.greenup.R
import com.example.greenup.databinding.ActivityEcoMoreMoreBinding
import com.example.greenup.databinding.ActivityReMoreMoreBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class EcoMoreMoreActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eco_more_more)
        auth = FirebaseAuth.getInstance()
        val binding = DataBindingUtil.setContentView<ActivityEcoMoreMoreBinding>(this,
            R.layout.activity_eco_more_more
        )

        val intent = intent
        val title = intent.getStringExtra("title").toString() // 데이터 가져오기
        Log.e("dddd",title)
        Log.e("dddd","응애?")

        val db = FirebaseFirestore.getInstance()
        db.collection("Eco")
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val data = document.data
                    // 데이터 처리 로직 추가
                    var a = data["title"] as String
                    var b = data["day"] as String
                    var c = data["context"] as String
                    if(a == title){
                        binding.title = a
                        binding.day = b
                        binding.context = c

                    }
                }

            }
            .addOnFailureListener { exception ->
            }
        FirebaseApp.initializeApp(this)


        // Firebase Storage 참조
        val storageRef = FirebaseStorage.getInstance().reference

        // 사진 파일을 가져올 Storage 경로
        val photoRef: StorageReference = storageRef.child("Eco/${title}.png")

        // 다운로드 URL 가져오기
        photoRef.downloadUrl
            .addOnSuccessListener { uri ->
                // 다운로드 URL을 사용하여 사진을 표시하거나 처리
                val downloadUrl = uri.toString()
                displayImageFromUrl(this, downloadUrl, binding.img)
            }
            .addOnFailureListener { exception ->
                Log.e("dddd",exception.toString())
                Log.e("dddd",title.toString())
            }
    }

    private fun displayImageFromUrl(context: Context, imageUrl: String, imageView: ImageView) {
        Glide.with(context)
            .load(imageUrl)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
            .into(imageView)
    }
}