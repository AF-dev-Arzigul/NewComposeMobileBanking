package com.example.newcomposemobilebanking.model.remote.api

import com.example.newcomposemobilebanking.model.remote.respone.CardItemResponse
import retrofit2.Response
import retrofit2.http.GET

/*
* Arzigul Nazarbaeva
* 12/18/2022, Sunday, 6:51 PM
*/

interface CardApi {

    @GET("card")
    fun getCards(): Response<List<CardItemResponse>>


}