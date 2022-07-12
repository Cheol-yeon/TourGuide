package com.example.kotlinprj

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.room.ColumnInfo
import com.example.kotlinprj.attractioncategory.AttractionDataInterface
import com.example.kotlinprj.foodcategory.FoodDataInterface
import com.example.roomtestkt.RoomAccess
import com.example.kotlinprj.foodcategory.RoomFoodData
import com.example.kotlinprj.foodcategory.getFoodKr
import com.kakao.sdk.common.util.Utility
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // StatusBar 아래로 앱이 실행되게 statusBar의 높이만큼 Padding을 줌
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        container.setPadding(0, statusBarHeight(this@IntroActivity), 0, 0)

        Log.e("IntroActivity", "onCreate")

        var keyHash = Utility.getKeyHash(this)
        Log.e("Hash", "${keyHash}")

        val foodRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/6260000/FoodService/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val foodService = foodRetrofit.create(FoodDataInterface::class.java)

        //Food Data
        foodService.getFoodData().enqueue(object : Callback<getFoodKr?> {
            override fun onResponse(
                call: Call<getFoodKr?>,
                responseFood: Response<getFoodKr?>
            ) {
                if(responseFood.isSuccessful) {
                    Log.e("IntroActivity", "Food onResponse 성공 : ${responseFood.body()!!.getFoodKr.items.size}")
                    if(RoomAccess(this@IntroActivity).foodGetCount() != responseFood.body()!!.getFoodKr.items.size) {
                        for (i in 0 until responseFood.body()!!.getFoodKr.items.size) {
                            RoomAccess(this@IntroActivity).foodInsertAll(
                                RoomFoodData(i+1,
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.main_title?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.sub_title?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.gugun_nm?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.lat,
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.lng,
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.addr1?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.cntct_tel?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.homepage_url?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.usage_day_week_and_time?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.menu?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.main_img?.trim(),
                                    responseFood.body()?.getFoodKr?.items?.get(i)?.itemcntnts?.trim())
                            )
                        }
                    }
                } else {
                    Log.e("IntroActivity", "Food onResponse 실패")
                }
            }

            override fun onFailure(call: Call<getFoodKr?>, t: Throwable) {
                Log.e("IntroActivity", "OnFailure 에러: ${t.message.toString()}")
            }
        })

//        val attractionRetrofit: Retrofit = Retrofit.Builder()
//            .baseUrl("http://apis.data.go.kr/6260000/AttractionService/")
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        val attractionService = attractionRetrofit.create(AttractionDataInterface::class.java)

        //Attraction Data
//        attractionService.getAttractionData().enqueue(object : Callback<getAttractionKr?> {
//            override fun onResponse(
//                call: Call<getAttractionKr?>,
//                responseAttr: Response<getAttractionKr?>
//            ) {
//                if(responseAttr.isSuccessful) {
//                    Log.e("IntroActivity", "Attraction onResponse 성공 : ${responseAttr.body()!!.getAttractionKr.item.size}")
//                    if(RoomAccess(this@IntroActivity).foodGetCount() != responseAttr.body()!!.getAttractionKr.item.size) {
//                        for (i in 0 until responseAttr.body()!!.getAttractionKr.item.size) {
////                            RoomAccess(this@IntroActivity).foodInsertAll(
////                                RoomAttractionData(
////                            )
//                        }
//                    }
//                } else {
//                    Log.e("IntroActivity", "Attraction onResponse 실패")
//                }
//            }
//
//            override fun onFailure(call: Call<getAttractionKr?>, t: Throwable) {
//                Log.e("IntroActivity", "OnFailure 에러: ${t.message.toString()}")
//            }
//        })

        val handler = Handler()
        handler.postDelayed(Runnable {
            Log.e("IntroActivity", "startActivity")
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

    fun statusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) context.resources.getDimensionPixelSize(resourceId)
        else 0
    }
}