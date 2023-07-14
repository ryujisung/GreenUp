package com.example.greenup.model.remote

import com.google.gson.annotations.SerializedName

data class FoodInfoGData(
    @SerializedName("allCnt") val allCnt: Int,
    @SerializedName("code") val code: Int,
    @SerializedName("codeMsg") val codeMsg: String,
    @SerializedName("ano") val ano: String,
    @SerializedName("infoSj") val infoSj: String,
    @SerializedName("infoRgsde") val infoRgsde: String,
    @SerializedName("infoModde") val infoModde: String,
    @SerializedName("infoUrl") val infoUrl: String,
    @SerializedName("tmnlImgUrl") val tmnlImgUrl: String
)
