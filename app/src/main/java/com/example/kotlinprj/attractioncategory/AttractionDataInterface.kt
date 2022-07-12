package com.example.kotlinprj.attractioncategory

import com.example.kotlinprj.foodcategory.getFoodKr
import com.example.kotlinprj.getAttractionKr
import retrofit2.Call
import retrofit2.http.GET

private const val keyValue = "Lnri2T3D%2BPbiuDyiPlOzf6JJlQOjeN5wgZ88tu28gP1aH7PPR4PDcd%2FmEMCIk%2F2IeFUUmY%2BdbjPXE9KZHpA%2Beg%3D%3D"
interface AttractionDataInterface {
    @GET("getAttractionKr?serviceKey=${keyValue}&numOfRows=999&resultType=json")
    fun getAttractionData(): Call<getAttractionKr>

}