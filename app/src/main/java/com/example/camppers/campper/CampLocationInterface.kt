package com.example.camppers.campper

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CampLocationInterface {
    @GET("/B551011/GoCamping/locationBasedList")
    fun getCampList(
        @Query("serviceKey") key: String,
        @Query("_type") type: String,
        @Query("MobileApp") mobileApp: String,
        @Query("MobileOS") mobileOS: String,
        @Query("mapX") mapX: String,
        @Query("mapY") mapY: String,
        @Query("radius") radius: String // 거리 반경 단위 'M' 최대 20000M
    ): Call<CampData>
}