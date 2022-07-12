package com.example.kotlinprj.foodcategory

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinprj.foodcategory.FoodDataDao
import com.example.kotlinprj.foodcategory.RoomFoodData

@Database(entities = arrayOf(RoomFoodData::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract  fun FoodDataDao(): FoodDataDao
}