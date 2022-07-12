package com.example.kotlinprj

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RoomAttractionData (
    @PrimaryKey
    val attractionItem_no: Int?,

    @ColumnInfo("MAIN_TITLE")
    val main_title: String?,

    @ColumnInfo("SUBTITLE")
    val sub_title: String?,

    @ColumnInfo("TITLE")
    val title: String?,

    @ColumnInfo("ADDR1")
    val addr1: String?,

    @ColumnInfo("GUGUN_NM")
    val gugun_nm: String?,

    @ColumnInfo("HOMEPAGE_URL")
    val homepage_url: String?,

    @ColumnInfo("LAT")
    val lat: Double?,

    @ColumnInfo("LNG")
    val lng: Double?,

    @ColumnInfo("TRFC_INFO")
    val trfc_info: String?,

    @ColumnInfo("USAGE_DAY_WEEK_AND_TIME")
    val usage_day_week_and_time: String?,

    @ColumnInfo("HLDY_INFO")
    val hldy_info: String?,

    @ColumnInfo("MIDDLE_SIZE_RM1")
    val middle_size_rm1: String?,

    @ColumnInfo("MAIN_IMG_NORMAL")
    val main_img: String?,

    @ColumnInfo("ITEMCNTNTS")
    val itemsntns: String?

)
