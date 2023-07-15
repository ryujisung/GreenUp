package com.example.greenup.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface FoodInfoNomalService {
    @GET
    fun getApiResponses(@Url url: String,
                        @Query("serviceKey") serviceKey: String,
                        @Query("pageNo") pageNo: Int,
                        @Query("cntPerPage") cntPerPage: Int,
                        @Query("productNm") productNm: String): Call<NS2>
}