package com.example.newcomposemobilebanking.util

import com.google.gson.annotations.SerializedName

data class UpdateTokenResponse(
    @SerializedName("access-token")
    val `access-token`: String,
    @SerializedName("refresh-token")
    val `refresh-token`: String
)
