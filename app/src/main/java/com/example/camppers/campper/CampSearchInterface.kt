package com.example.camppers.campper

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CampSearchInterface {
    @GET("/B551011/GoCamping/searchList")
    fun getCampList(
        @Query("serviceKey") key: String,
        @Query("_type") type: String,
        @Query("MobileApp") mobileApp: String,
        @Query("MobileOS") mobileOS: String,
        @Query("keyword") keyWord: String
    ): Call<CampData>
}