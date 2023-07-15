package com.example.greenup.model.remote

import com.squareup.moshi.Json



data class FoodInfoNomalData(
    @Json(name = "allCnt") val allCnt: Int,
    @Json(name = "code") val code: Int,
    @Json(name = "codeMsg") val codeMsg: String
)
