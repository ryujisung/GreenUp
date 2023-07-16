package com.example.greenup.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greenup.R
import com.example.greenup.model.remote.FoodSearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class FoodSearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_search)

        var  foodSearchET = findViewById<android.widget.EditText>(R.id.et_food_search)
        var  foodSearchBtn = findViewById<ImageButton>(R.id.btn_food_search)

        val recyclerView: RecyclerView = findViewById(R.id.rv_food_search)
        val adapter = FoodSerchAdapter()

        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2는 한 행에 들어갈 아이템 수입니다.
        recyclerView.adapter = adapter

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://52.195.176.143/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(FoodSearchService::class.java)

        foodSearchBtn.setOnClickListener {
            val query = foodSearchET.text.toString()
            GlobalScope.launch(Dispatchers.Main) {
                val products = service.getProducts(query)  // Ensure to unwrap the response
                if (products != null) {
                    adapter.updateItems(products)
                }
            }
        }

    }
}