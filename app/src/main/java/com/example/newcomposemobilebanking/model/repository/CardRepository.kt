package com.example.newcomposemobilebanking.model.repository

import com.example.newcomposemobilebanking.model.local.LocalStorage
import com.example.newcomposemobilebanking.model.remote.api.CardApi
import com.example.newcomposemobilebanking.model.remote.respone.CardItemResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.newcomposemobilebanking.util.ResultData
import javax.inject.Inject
import javax.inject.Singleton

/*
 * Arzigul Nazarbaeva
 * 12/18/2022, Sunday, 6:56 PM
*/


@Singleton
class CardRepository @Inject constructor(
    private val localStorage: LocalStorage,
    private val cardApi: CardApi
) {

    fun getCards(): Flow<ResultData<List<CardItemResponse>?>> = flow {
        val response = cardApi.getCards()

        if (response.isSuccessful) {
            if (response.body() != null) {
                emit(ResultData.Success(response.body()))
            } else {
                emit(ResultData.Error(response.message()))
            }
        } else {
            emit(ResultData.Error(response.message()))
        }
    }

}