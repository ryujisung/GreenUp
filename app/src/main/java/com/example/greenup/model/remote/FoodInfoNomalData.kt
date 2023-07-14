package com.example.greenup.model.remote

import com.google.gson.annotations.SerializedName

data class FoodInfoNomalData(
    @SerializedName("allCnt") val allCnt: Int,
    @SerializedName("code") val code: Int,
    @SerializedName("codeMsg") val codeMsg: String
)
