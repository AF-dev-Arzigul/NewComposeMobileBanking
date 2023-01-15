package com.example.newcomposemobilebanking.model.remote.api

import com.example.newcomposemobilebanking.model.remote.request.SignInRequest
import com.example.newcomposemobilebanking.model.remote.request.SignInVerifyRequest
import com.example.newcomposemobilebanking.model.remote.request.SignUpRequest
import com.example.newcomposemobilebanking.model.remote.request.SignUpVerifyRequest
import com.example.newcomposemobilebanking.model.remote.respone.SignInResponse
import com.example.newcomposemobilebanking.model.remote.respone.SignInVerifyResponse
import com.example.newcomposemobilebanking.model.remote.respone.SignUpResponse
import com.example.newcomposemobilebanking.model.remote.respone.SignUpVerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/sign-in")
    suspend fun signIn(
        @Body data: SignInRequest
    ): Response<SignInResponse>

    @POST("auth/sign-up")
    suspend fun signUp(
        @Body data: SignUpRequest
    ): Response<SignUpResponse>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(
        @Body data: SignUpVerifyRequest
    ): Response<SignUpVerifyResponse>

    @POST("auth/sign-in/verify")
    suspend fun signInVerify(
        @Body data: SignInVerifyRequest
    ): Response<SignInVerifyResponse>

}