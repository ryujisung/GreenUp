package com.example.greenup.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.greenup.utils.State
import com.example.greenup.model.remote.FoodApiRepository
import com.example.greenup.model.remote.FoodDBRepository
import com.example.greenup.model.data.Food
import kotlinx.coroutines.*

class FoodScanViewModel(application: Application) : ViewModel() {
    private val apiRepository = FoodApiRepository()
    private val roomRepository = FoodDBRepository(application)

    val barCodeId = MutableLiveData<String>()
    val foodData = MutableLiveData<Food>()
    val apiState = MutableLiveData<State>()
    val roomState = MutableLiveData<State>()

    var foodName = MutableLiveData<String>()
    var foodCompany = MutableLiveData<String>()
    var foodCategory = MutableLiveData<String>()
    var foodKind = MutableLiveData<String>()
    var foodId = MutableLiveData<String>()
    //var foodDate = MutableLiveData<String>()



    fun getFoodDataByBarcodeId() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            apiState.value = State.FAIL
            Log.e("foodApi", "API Error ${e.message}")

            if(e is com.squareup.moshi.JsonDataException) {
                Log.e("foodApi", "알수없는 데이터 입니다.")
            }
        }) {
            apiState.value = State.LOADING

            val res = withContext(Dispatchers.IO) {
                apiRepository.getFoodData(barcodeID = barCodeId.value!!)
            }

            if (res.isSuccessful && res.body()!!.C005.total_count >= 1) {
                val body = res.body()!!
                apiState.value = State.OK

                foodData.value = body.C005.row[0]
                Log.d("foodApi", body.C005.row[0].foodName)
                foodName = MutableLiveData(body.C005.row[0].foodName)
                foodCompany = MutableLiveData(body.C005.row[0].companyName)
                foodCategory = MutableLiveData(body.C005.row[0].foodKind)
                foodId = MutableLiveData(body.C005.row[0].foodId)
                foodKind = MutableLiveData(body.C005.row[0].foodKind)
                //foodDate = MutableLiveData(body.C005.row[0].date)


            } else {
                apiState.value = State.FAIL
            }
        }
    }

    fun insertFood() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            roomState.value = State.FAIL
        }) {
            roomState.value = State.LOADING
            withContext(Dispatchers.IO) {
                roomRepository.insertFood(foodData.value!!)
            }
            roomState.value = State.OK

            Log.d("foodInsert", "음식 추가 완료")
        }
    }
}