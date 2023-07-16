package com.example.greenup.model.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface FoodSearchService {
    @GET("/scrap")
    suspend fun getProducts(@Query("query") query: String): List<FoodSearch>
}