package com.example.newcomposemobilebanking.model.remote.respone

import com.google.gson.annotations.SerializedName

/*
 * Arzigul Nazarbaeva
 * 12/18/2022, Sunday, 6:55 PM
*/


data class CardItemResponse(
    @SerializedName("expired-month")
    val expiredMonth: Int, // 1
    @SerializedName("expired-year")
    val expiredYear: Int, // 2023
    @SerializedName("id")
    val id: Int, // 10
    @SerializedName("is-visible")
    val isVisible: Boolean, // true
    @SerializedName("name")
    val name: String, // Personal
    @SerializedName("pan")
    val pan: String, // 12345678
    @SerializedName("theme-type")
    val themeType: Int // 2
)
