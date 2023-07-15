package com.example.greenup.model.remote

import android.app.Application
import com.example.greenup.model.data.Food
import com.example.greenup.model.remote.FoodDao
import com.example.greenup.model.remote.FoodDatabase
import java.time.LocalDateTime

class FoodDBRepository(application: Application) {
    private val foodDao : FoodDao by lazy {
        FoodDatabase.getDatabase(application).foodDao()
    }

    fun insertFood(foodData : Food) {
        foodDao.insert(foodData)
    }

    fun getAll() : List<Food> {
        return foodDao.getFoodData()
    }

    fun getAllDate(date : LocalDateTime = LocalDateTime.now()) : List<Food> {
        return foodDao.getFoodDay(date.toString())
    }
}