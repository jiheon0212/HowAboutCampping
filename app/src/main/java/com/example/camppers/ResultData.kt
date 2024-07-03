package com.example.camppers

import java.io.Serializable

data class ResultData(
    val imageUrl: String,
    val name: String,
    val address: String,
    val status: String,
    val lineIntro: String,
    val intro: String,
    val featureIntro: String,
    val animalCarry: String,
    val serviceList: String,
    val reservationUrl: String,
    val reservationRoute: String,
    val tel: String
): Serializable
