package com.example.greenup.model.remote

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("I2570") val data: DataResponse
)

data class DataResponse(
    @SerializedName("total_count") val totalCount: String,
    @SerializedName("row") val rows: List<Row>,
    @SerializedName("RESULT") val result: Result
)

data class Row(
    @SerializedName("PRDLST_REPORT_NO") val reportNo: String,
    @SerializedName("CMPNY_NM") val companyNm: String,
    @SerializedName("PRDT_NM") val prdtNm: String,
    @SerializedName("LAST_UPDT_DTM") val lastUpdtDtm: String,
    @SerializedName("PRDLST_NM") val prdlstNm: String,
    @SerializedName("HRNK_PRDLST_NM") val hrnkPrdlstNm: String,
    @SerializedName("HTRK_PRDLST_NM") val htrkPrdlstNm: String
    // add the rest of the fields here
)

data class Result(
    @SerializedName("MSG") val msg: String,
    @SerializedName("CODE") val code: String
)
