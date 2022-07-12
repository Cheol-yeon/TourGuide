package com.example.roomtestkt

import android.content.Context
import androidx.room.Room
import com.example.kotlinprj.RoomAttractionData
import com.example.kotlinprj.foodcategory.AppDatabase
import com.example.kotlinprj.foodcategory.RoomFoodData

class RoomAccess(context: Context) {
//    constructor(context: RecyclerView.Adapter<FoodAdapter.ViewHolder>) : this(IntroActivity())

    val foodDB = Room.databaseBuilder(context, AppDatabase::class.java, "jojoDB").build()

    fun foodInsertAll(user: RoomFoodData) {

        val insertTread = Thread(Runnable {
            foodDB.FoodDataDao().insertAll(user)
        })
        insertTread.start()
        foodDB.close()
    }

    fun foodGetAll(): List<RoomFoodData> {
        var foodData = listOf<RoomFoodData>()
        val getAllThread = Thread(Runnable {
            foodData = foodDB.FoodDataDao().getAll()
        })
        getAllThread.start()
        getAllThread.join()
        foodDB.close()
        return foodData
    }

    fun foodGetCount(): Int {
        var count: Int = 0
        val countThread = Thread(Runnable {
            count = foodDB.FoodDataDao().getCount()
        })
        countThread.start()
        countThread.join()
        foodDB.close()
        return count
    }

    fun foodGetById(id: Int): RoomFoodData {
        var data: RoomFoodData? = null
        val getThread = Thread(Runnable {
            data = foodDB.FoodDataDao().loadById(id)
        })
        getThread.start()
        getThread.join()
        foodDB.close()
        return data!!
    }

    fun foodGetAllById(items: ArrayList<Int>): List<RoomFoodData> {
        var foodData: List<RoomFoodData>? = null
        val getAllThread = Thread(Runnable {
            foodData = foodDB.FoodDataDao().getAllById(items)
        })
        getAllThread.start()
        getAllThread.join()
        foodDB.close()
        return foodData!!
    }

//    fun attractionInsertAll(user: RoomAttractionData) {
//
//        val insertTread = Thread(Runnable {
//            foodDB.FoodDataDao().insertAll(user)
//        })
//        insertTread.start()
//        foodDB.close()
//    }

}
