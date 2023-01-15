package com.example.newcomposemobilebanking.model.remote.respone

import com.google.gson.annotations.SerializedName

data class SignUpVerifyResponse(
    @SerializedName("access-token")
    val accessToken: String,
    @SerializedName("refresh-token")
    val refreshToken: String
)
