package com.example.kotlinprj.foodcategory

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RoomFoodData(
    @PrimaryKey
    val item_no: Int,

    @ColumnInfo(name = "MAIN_TITLE")
    val main_title: String?,

    @ColumnInfo(name = "SUB_TITLE")
    val sub_title: String?,

    @ColumnInfo(name = "GUGUN_NM")
    val gugun_nm: String?,

    @ColumnInfo(name = "LAT")
    val lat: Double?,

    @ColumnInfo(name = "LNG")
    val lng: Double?,

    @ColumnInfo(name = "ADDR1")
    val addr1: String?,

    @ColumnInfo(name = "CNTCT_TEL")
    val cntct_tel: String?,

    @ColumnInfo(name = "HOMEPAGE_URL")
    val homepage_url: String?,

    @ColumnInfo(name = "USAGE_DAY_WEEK_AND_TIME")
    val usage_day_week_and_time: String?,

    @ColumnInfo(name = "RPRSNTV_MENU")
    val menu: String?,

    @ColumnInfo(name = "MAIN_IMG_NORMAL")
    val main_img: String?,

    @ColumnInfo(name = "ITEMCNTNTS")
    val itemcntnts: String?
)