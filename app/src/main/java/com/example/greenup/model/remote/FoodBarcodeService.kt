package com.example.greenup.model.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodBarcodeService {
    @GET("{serviceId}/{dataType}/{startIdx}/{endIdx}/{BRCD_NO}")
    fun getFoodApiData(
        @Path("serviceId") serviceId: String,
        @Path("dataType") dataType: String,
        @Path("startIdx") startIdx: Int,
        @Path("endIdx") endIdx: Int,
        @Path("BRCD_NO") BRCD_NO: String,
        @Query("keyId") keyId: String
    ): Call<FoodBarcodeData>
}