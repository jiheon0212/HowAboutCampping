package com.example.camppers.campper

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CampData(
    @Expose
    @SerializedName("response")
    val response: Response
) {
    data class Response(
        @Expose
        @SerializedName("body")
        val body: Body,
        @Expose
        @SerializedName("header")
        val header: Header
    ) {
        data class Body(
            @Expose
            @SerializedName("items")
            val items: Items
        ) {
            data class Items(
                @Expose
                @SerializedName("item")
                val item: List<Item>
            ) {
                data class Item(
                    @Expose
                    @SerializedName("addr1")
                    val addr1: String, // 주소
                    @Expose
                    @SerializedName("animalCmgCl")
                    val animalCmgCl: String, // 동물 출입
                    @Expose
                    @SerializedName("facltNm")
                    val facltNm: String, // 캠핑장 이름
                    @Expose
                    @SerializedName("featureNm")
                    val featureNm: String, // 기능 설명
                    @Expose
                    @SerializedName("firstImageUrl")
                    val firstImageUrl: String, // 대표 이미지
                    @Expose
                    @SerializedName("homepage")
                    val homepage: String, // 홈페이지
                    @Expose
                    @SerializedName("intro")
                    val intro: String, // 소개
                    @Expose
                    @SerializedName("lineIntro")
                    val lineIntro: String, // 한 줄 소개
                    @Expose
                    @SerializedName("manageSttus")
                    val manageSttus: String, // 운영 여부
                    @Expose
                    @SerializedName("mapX")
                    val mapX: String,
                    @Expose
                    @SerializedName("mapY")
                    val mapY: String,
                    @Expose
                    @SerializedName("resveCl")
                    val resveCl: String, // 예약 방법
                    @Expose
                    @SerializedName("resveUrl")
                    val resveUrl: String, // 예약 주소
                    @Expose
                    @SerializedName("sbrsCl")
                    val sbrsCl: String, // 편의 기능
                    @Expose
                    @SerializedName("tel")
                    val tel: String // 전화 번호
                )
            }
        }

        data class Header(
            @Expose
            @SerializedName("resultCode")
            val resultCode: String,
            @Expose
            @SerializedName("resultMsg")
            val resultMsg: String
        )
    }
}
