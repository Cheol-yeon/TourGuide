package com.example.kotlinprj.foodcategory

import androidx.room.*
import kotlin.collections.ArrayList

@Dao
interface FoodDataDao {
    @Query("SELECT * FROM roomfooddata ORDER BY GUGUN_NM")
    fun getAll(): List<RoomFoodData>

    @Query("SELECT * FROM roomfooddata WHERE item_no = (:item_no)")
    fun loadById(item_no: Int): RoomFoodData

    @Query("SELECT * FROM roomfooddata WHERE item_no IN (:items)")
    fun getAllById(items: ArrayList<Int>): List<RoomFoodData>

    @Query("SELECT COUNT(*) FROM roomfooddata")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: RoomFoodData)

    @Delete
    fun delete(user: RoomFoodData)
}