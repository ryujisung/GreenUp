package com.example.greenup.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.greenup.R
import com.example.greenup.model.remote.FoodBarcodeData
import com.example.greenup.model.remote.FoodBarcodeService
import com.example.greenup.model.remote.FoodInfoNomalData
import com.example.greenup.model.remote.FoodInfoNomalService
import com.example.greenup.model.remote.MainResponse
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class FoodSearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_search_result)
        val foodImgView = findViewById<ImageView>(R.id.FoodImgView)
        val resultTextView = findViewById<TextView>(R.id.ResultTextView)
        var imgUrl = "https://www.consumer.go.kr/openapi/contents/image/00000463/1.jpg"

        //인텐트 barcodeId 받아서 해당하는 식품 정보 띄우기
        val barcodeId = intent.getStringExtra("barcodeId")
        val foodName = intent.getStringExtra("foodName")
        val foodCompany = intent.getStringExtra("foodCompany")
        val foodCategory = intent.getStringExtra("foodCategory")

        /*
        var cmpny_nm = ""
        var prdt_nm = ""
        var prdlst_nm = ""
        var hrnk_prdlst_nm = ""
        var htrk_prdlst_nm = ""

        var gson = GsonBuilder().setLenient().create()
        //val mainResponse: MainResponse = gson.fromJson(jsonString, MainResponse::class.java)


        //api 통신해서 바코드 -> 상품명, 제조사, 분류 가져오기
        val retrofit = Retrofit.Builder()
            .baseUrl("http://openapi.foodsafetykorea.go.kr/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            //.also { println(it.toString()) } // Add this line


        val service = retrofit.create(FoodBarcodeService::class.java)
        val call = service.getFoodApiData(
            "I2570", "json", 1, 100, barcodeId.toString(), "b1e4d2ecdb5a4851addd"
        )

        call.enqueue(object : Callback<FoodBarcodeData> {
            override fun onResponse(
                call: Call<FoodBarcodeData>,
                response: Response<FoodBarcodeData>
            ) {
                if (response.isSuccessful) {
                    //val apiResponse = response.body()
                    val apiResponse = JSONObject(response.body()?.toString() ?: "")
                    Log.d("Retrofit", "성공 : ${apiResponse.toString()}")

                    cmpny_nm = apiResponse.optString("CMPNY_NM")
                    prdt_nm = apiResponse.optString("PRDT_NM")
                    prdlst_nm = apiResponse.optString("PRDLST_NM")
                    hrnk_prdlst_nm = apiResponse.optString("HRNK_PRDLST_NM")
                    htrk_prdlst_nm = apiResponse.optString("HTRK_PRDLST_NM")
                    Log.d("Retrofit", "성공 : $cmpny_nm $prdt_nm $prdlst_nm $hrnk_prdlst_nm $htrk_prdlst_nm")

                } else {
                    //Log.d("Retrofit", "실패 : ${response.body()}")
                    Log.d("Retrofit", "실패 : ${response.raw()} ${response.code()} ${response.message()} ${response.errorBody()} ${response.headers()} ${response.isSuccessful} ${response.body()} ")


                }
            }

            override fun onFailure(call: Call<FoodBarcodeData>, t: Throwable) {

                Log.d("Retrofit", "실패 : $t")
            }
        })
        */

        //api 통신해서 인증정보 가져오기
        val retrofitService2 = Retrofit.Builder()
            .baseUrl("https://www.consumer.go.kr/openapi/crtfcs/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service2 = retrofitService2.create(FoodInfoNomalService::class.java)

        // List to store responses
        //val responses = mutableListOf<FoodInfoNomalData>()
        var foodConResponses = mutableListOf<Int>()

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
            val url = "${i.toString().padStart(2, '0')}?serviceKey=QHGSAHDGCP&pageNo=1&cntPerPage=100&productNm=${foodName}"
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


        //이미지 띄우기
        val shClient = OkHttpClient()

        //검색어 생성
        val sercht = "${foodCompany} ${foodName}"
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("www.googleapis.com")
            .addPathSegment("customsearch")
            .addPathSegment("v1")
            .addQueryParameter("q", sercht) //search term
            .addQueryParameter("cx", "107624a81d84b44e6") // Custom Search Engine ID
            .addQueryParameter("key", "AIzaSyApqZAoIfENdXkhWueos4ZZVvjCRdG4uxg") // API key
            .addQueryParameter("searchType", "image")
            .addQueryParameter("num", "1")
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        shClient.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call:okhttp3. Call, response: okhttp3.Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val responseData = response.body?.string()
                    if (responseData != null) {
                        val json = JSONObject(responseData)
                        val items = json.getJSONArray("items")
                        val firstItem = items.getJSONObject(0)
                        imgUrl = firstItem.getString("link")
                        //println(link)
                    }
                }
            }
        })




        //Glide.with(this).load(imgUrl).into(foodImgView)


    }
}