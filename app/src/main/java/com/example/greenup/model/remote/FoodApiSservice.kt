package com.heechan.iampig.model.remote
import com.heechan.iampig.model.data.FoodApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FoodApiSservice {
    @GET("/api/7a08e46bb71c4202a067/C005/json/1/2/BAR_CD={barCodeID}")
    suspend fun getFoodData(@Path("barCodeID") barcodeID : String) : Response<FoodApiResponse>
}