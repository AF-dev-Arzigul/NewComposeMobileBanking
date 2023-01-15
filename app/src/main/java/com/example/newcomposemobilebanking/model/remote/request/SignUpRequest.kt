package com.example.newcomposemobilebanking.model.remote.request

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("first-name") val `first-name`: String,
    @SerializedName("last-name") val `last-name`: String,
    @SerializedName("born-date") val `born-date`: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("password") val password: String
)