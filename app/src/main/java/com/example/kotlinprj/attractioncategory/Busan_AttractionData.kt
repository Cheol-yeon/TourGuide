package com.example.kotlinprj

import com.google.gson.annotations.SerializedName

data class getAttractionKr(
    @SerializedName("getAttractionKr")
    val getAttractionKr: item
)

data class item (
    @SerializedName("item")
    val item: List<Busan_AttractionData>
)

data class Busan_AttractionData(
    val attractionItem_no: Int?,

    @SerializedName("MAIN_TITLE")
    val main_title: String?,

    @SerializedName("SUBTITLE")
    val sub_title: String?,

    @SerializedName("TITLE")
    val title: String?,

    @SerializedName("ADDR1")
    val addr1: String?,

    @SerializedName("GUGUN_NM")
    val gugun_nm: String?,

    @SerializedName("HOMEPAGE_URL")
    val homepage_url: String?,

    @SerializedName("LAT")
    val lat: Double?,

    @SerializedName("LNG")
    val lng: Double?,

    @SerializedName("TRFC_INFO")
    val trfc_info: String?,

    @SerializedName("USAGE_DAY_WEEK_AND_TIME")
    val usage_day_week_and_time: String?,

    @SerializedName("HLDY_INFO")
    val hldy_info: String?,

    @SerializedName("MIDDLE_SIZE_RM1")
    val middle_size_rm1: String?,

    @SerializedName("MAIN_IMG_NORMAL")
    val main_img: String?,

    @SerializedName("ITEMCNTNTS")
    val itemsntns: String?

)
