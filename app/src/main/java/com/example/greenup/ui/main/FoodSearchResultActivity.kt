package com.example.greenup.ui.main

import FoodInfoGService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.greenup.R
import com.example.greenup.model.remote.FoodInfoGData
import com.example.greenup.model.remote.FoodInfoNomalData
import com.example.greenup.model.remote.FoodInfoNomalService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FoodSearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_search_result)
        val foodImgView = findViewById<ImageView>(R.id.FoodImgView)
        val resultTextView = findViewById<TextView>(R.id.ResultTextView)
        var imgUrl = "https://www.consumer.go.kr/openapi/contents/image/00000463/1.jpg"

        //인텐트 barcodeId 받아서 해당하는 식품 정보 띄우기
        val barcodeId = intent.getStringExtra("barcodeId")

        //api 통신해서 이미지 주소 가져오기
        val retrofitService1 = Retrofit.Builder()
            .baseUrl("https://www.consumer.go.kr/openapi/contents/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofitService1.create(FoodInfoGService::class.java)
        val call = service.getImages("O1HC5UI05A", 1, 10)

        val foodConResponses = mutableListOf<Int>()
        //val totalRequests = 15 - 2 + 1
        //var completedRequests = 0

        call.enqueue(object : Callback<FoodInfoGData> {
            override fun onResponse(call: Call<FoodInfoGData>, response: Response<FoodInfoGData>) {
                if (response.isSuccessful) {
                    //Use response.body() to handle data.
                    Log.d("response", response.body().toString())
                    Log.d("response", response.body()?.tmnlImgUrl.toString())
                    imgUrl = response.body()?.tmnlImgUrl.toString()
                } else {
                    //Handle error
                    Log.d("error", "error api 1")

                }
            }

            override fun onFailure(call: Call<FoodInfoGData>, t: Throwable) {
                //Handle failure
                Log.d("error", "failure api 1")
            }
        })

        //이미지 띄우기
        Glide.with(this).load(imgUrl).into(foodImgView)

        //api 통신해서 인증정보 가져오기
        val retrofitService2 = Retrofit.Builder()
            .baseUrl("https://www.consumer.go.kr/openapi/crtfcs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service2 = retrofitService2.create(FoodInfoNomalService::class.java)

        // List to store responses
        //val responses = mutableListOf<FoodInfoNomalData>()

        fun processFoodNormalApiResponse(responses: List<Int>): String {
            // Define the mapping from the certificate numbers to their descriptions.
            val certificationInfo = mapOf(
                2 to "환경성적표지인증",
                3 to "KC인증",
                4 to "방송통신기자재 적합성평가",
                5 to "의료기관인증",
                6 to "어린이기호식품 품질인증",
                7 to "CCM인증",
                8 to "수산물품질인증",
                9 to "행복드림표지",
                10 to "HACCP인증",
                11 to "건강기능식품GMP",
                12 to "전통식품인증",
                13 to "의료기기GMP",
                14 to "술품질인증",
                15 to "친환경농산물인증"
            )

            val stringBuilder = StringBuilder()

            // Iterate over the responses.
            for ((index, value) in responses.withIndex()) {
                // Get the certification number, starting from 2.
                val certificationNumber = index + 2

                // If the response is 1, append the corresponding certification info to the string.
                if (value == 1) {
                    certificationInfo[certificationNumber]?.let {
                        stringBuilder.append(it)
                        stringBuilder.append(", ") // Separate with commas
                    }
                }
            }

            // Remove the last comma and return the result.
            if (stringBuilder.isNotEmpty()) {
                stringBuilder.setLength(stringBuilder.length - 2)
            }

            return stringBuilder.toString()
        }

        for (i in 2..15) { // Adjust this range as needed
            val url = "${i.toString().padStart(2, '0')}?serviceKey=QHGSAHDGCP&pageNo=1&cntPerPage=100&productNm=%EC%82%BC%EB%8B%A4%EC%88%98"
            val call = barcodeId?.let { service2.getApiResponses(url, "QHGSAHDGCP", 1, 100, it) }

            call!!.enqueue(object : Callback<FoodInfoNomalData> {
                override fun onResponse(call: Call<FoodInfoNomalData>, response: Response<FoodInfoNomalData>) {
                    if (response.isSuccessful) {
                        //responses.add(response.body()!!)

                        //i값에 따른 다른 변수에 저장
                        Log.d("response", response.body()!!.codeMsg)
                        if (response.body()?.codeMsg == "정상") {
                            foodConResponses.add(1)
                        } else {
                            foodConResponses.add(0)
                        }

                    } else {
                        //Handle error
                        Log.d("error", "error api 2")
                    }
                }

                override fun onFailure(call: Call<FoodInfoNomalData>, t: Throwable) {
                    //Handle failure
                    Log.d("error", "failure api 2")
                }
            })
        }
        val foodConRes = processFoodNormalApiResponse(foodConResponses)
        resultTextView.text = foodConRes

    }
}