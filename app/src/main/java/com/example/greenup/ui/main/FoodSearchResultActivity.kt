package com.example.greenup.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.greenup.R
import com.example.greenup.model.remote.FoodImgData
import com.example.greenup.model.remote.FoodInfoNomalService
import com.example.greenup.model.remote.NS2
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.io.IOException

class FoodSearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_search_result)
        val foodImgView = findViewById<ImageView>(R.id.FoodImgView)
        val resultTextView = findViewById<TextView>(R.id.ResultTextView)
        var imgUrl = "https://www.consumer.go.kr/openapi/contents/image/00000463/1.jpg"

        //인텐트 barcodeId 받아서 해당하는 식품 정보 띄우기
        //val barcodeId = intent.getStringExtra("barcodeId")
        val foodName = intent.getStringExtra("foodName")
        val foodCompany = intent.getStringExtra("foodCompany")
        val foodCategory = intent.getStringExtra("foodCategory")
        val foodKind = intent.getStringExtra("foodKind")
        val foodId = intent.getStringExtra("foodId")


        val gson = GsonBuilder().setLenient().create()

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
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        val service2 = retrofitService2.create(FoodInfoNomalService::class.java)

        // List to store responses
        //val responses = mutableListOf<NS2>()
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
            val url = "${i.toString().padStart(2, '0')}"
            //Log.d("url", url)
            val call = service2.getApiResponses(url, "QHGSAHDGCP", 1, 100, "삼다수")

            call!!.enqueue(object : Callback<NS2> {
                override fun onResponse(call: Call<NS2>, response: Response<NS2>) {
                    if (response.isSuccessful) {
                        //responses.add(response.body()!!)

                        //i값에 따른 다른 변수에 저장
                        Log.d("response", response.body()?.channel?.returnData?.codeMsg.toString())
                        if (response.body()?.channel?.returnData?.codeMsg.toString() == "정상") {
                            foodConResponses.add(1)
                            Log.d("response", "1")

                            val foodConRes = processFoodNormalApiResponse(foodConResponses)
                            Log.d("response", foodConRes)
                            resultTextView.text = foodConRes

                        } else {
                            foodConResponses.add(0)
                        }

                    } else {
                        //Handle error
                        Log.d("error", "error api 2")
                    }
                }

                override fun onFailure(call: Call<NS2>, t: Throwable) {
                    //Handle failure
                    Log.d("error", "failure api 2 ${t.message}")
                }
            })
        }



        //이미지 띄우기
        val shClient = OkHttpClient()
        //검색어 생성
        val sercht = "${foodCompany} ${foodName}"
        Log.d("sercht", sercht)

        val url = HttpUrl.Builder()
            .scheme("https")
            .host("www.googleapis.com")
            .addPathSegment("customsearch")
            .addPathSegment("v1")
            .addQueryParameter("q", sercht) //search term
            .addQueryParameter("cx", "107624a81d84b44e6") // Custom Search Engine ID
            .addQueryParameter("key", "AIzaSyApqZAoIfENdXkhWueos4ZZVvjCRdG4uxg") // API key
            //.addQueryParameter("searchType", "image")
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
                if (response.isSuccessful) {
                    Log.d("!!!response", response.toString())
                    response.body?.string()?.let {
                        val json = JSONObject(it)
                        Log.d("!!!json", json.toString())
                        //cse_image 가져오기
                        val items = json.getJSONArray("items")
                        val item = items.getJSONObject(0)
                        val pagemap = item.getJSONObject("pagemap")
                        val cse_image = pagemap.getJSONArray("cse_image")
                        val cse_image_item = cse_image.getJSONObject(0)
                        val imgUrl = cse_image_item.getString("src")
                        Log.d("imgUrl", imgUrl)

                        runOnUiThread {
                            Glide.with(this@FoodSearchResultActivity).load(imgUrl).into(foodImgView)
                        }
                    }
                }
                else {
                    Log.d("error", "error api 3 ${response.message}")
                }
            }
        })
        Log.d("imgUrl", imgUrl)


        /*
        val url = "https://www.googleapis.com/customsearch/v1?" +
                "key=AIzaSyApqZAoIfENdXkhWueos4ZZVvjCRdG4uxg" +
                "&cx=107624a81d84b44e6" +
                "&searchType=image" +
                "&q=${sercht}"

        val request = Request.Builder()
            .url(url)
            .build()

        shClient.newCall(request).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let {
                        val gson = Gson()
                        val result = gson.fromJson(it, FoodImgData::class.java)

                        if(result.items.isNotEmpty()) {
                            // 첫 번째 이미지 링크를 가져옵니다.
                            val firstImageLink = result.items[0].link
                            println(firstImageLink)
                        }
                    }
                }
            }
        })
        */

    }
}