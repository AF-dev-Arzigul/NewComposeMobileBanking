package com.example.newcomposemobilebanking.util

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import com.example.newcomposemobilebanking.di.BASE_URL
import com.example.newcomposemobilebanking.model.local.LocalStorage

fun insertTokenInterceptor(
    storage: LocalStorage
) = Interceptor { chain ->
    val request = chain.request().newBuilder()
        .addHeader("authorization", "Bearer ${storage.accessToken}").build()
    chain.proceed(request)
}

fun refreshTokenInterceptor(
    storage: LocalStorage
) = Interceptor { chain ->
    val request = chain.request()
    val response = chain.proceed(request)

    if (response.code() == 401) {
        response.close()

        val data = JSONObject()
        data.put("refresh-token", storage.refreshToken)
        val body =
            RequestBody.create(MediaType.get("application/json; charset=utf-8"), data.toString())

        val requestRefresh = request.newBuilder()
            .method("POST", body)
            .url("${BASE_URL}auth/update-token")
            .build()

        val responseRefresh = chain.proceed(requestRefresh)
        if (responseRefresh.code() == 401) {
            return@Interceptor responseRefresh

            // refresh token ham eskirdi login screen navigate
        }

        if (responseRefresh.code() == 200) {
            responseRefresh.body()?.let {
                val refreshData = Gson().fromJson(it.string(), UpdateTokenResponse::class.java)
                storage.refreshToken = refreshData.`refresh-token`
                storage.accessToken = refreshData.`access-token`
            }

            responseRefresh.close()

            val requestSecond = request.newBuilder()
                .removeHeader("token")
                .addHeader("token", storage.accessToken)
                .build()

            return@Interceptor chain.proceed(requestSecond)
        }
    }

    return@Interceptor response
}