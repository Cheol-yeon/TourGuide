package com.example.kotlinprj.foodcategory

import com.google.gson.annotations.SerializedName

data class getFoodKr(
    @SerializedName("getFoodKr")
    val getFoodKr: items
)

data class items(
    @SerializedName("item")
    val items: List<Busan_FoodData>
)

data class Busan_FoodData(
    val foodItem_no: Int?,

    @SerializedName("MAIN_TITLE")
    val main_title: String?,

    @SerializedName("SUB_TITLE")
    val sub_title: String?,

    @SerializedName("GUGUN_NM")
    val gugun_nm: String?,

    @SerializedName("LAT")
    val lat: Double?,

    @SerializedName("LNG")
    val lng: Double?,

    @SerializedName("ADDR1")
    val addr1: String?,

    @SerializedName("CNTCT_TEL")
    val cntct_tel: String?,

    @SerializedName("HOMEPAGE_URL")
    val homepage_url: String?,

    @SerializedName("USAGE_DAY_WEEK_AND_TIME")
    val usage_day_week_and_time: String?,

    @SerializedName("RPRSNTV_MENU")
    val menu: String?,

    @SerializedName("MAIN_IMG_NORMAL")
    val main_img: String?,

    @SerializedName("ITEMCNTNTS")
    val itemcntnts: String?
)
