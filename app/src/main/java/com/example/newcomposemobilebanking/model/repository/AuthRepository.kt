package com.example.newcomposemobilebanking.model.repository

import com.example.newcomposemobilebanking.model.local.LocalStorage
import com.example.newcomposemobilebanking.model.remote.api.AuthApi
import com.example.newcomposemobilebanking.model.remote.request.SignInRequest
import com.example.newcomposemobilebanking.model.remote.request.SignInVerifyRequest
import com.example.newcomposemobilebanking.model.remote.request.SignUpRequest
import com.example.newcomposemobilebanking.model.remote.request.SignUpVerifyRequest
import com.example.newcomposemobilebanking.model.remote.respone.SignInResponse
import com.example.newcomposemobilebanking.model.remote.respone.SignInVerifyResponse
import com.example.newcomposemobilebanking.model.remote.respone.SignUpResponse
import com.example.newcomposemobilebanking.model.remote.respone.SignUpVerifyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.example.newcomposemobilebanking.util.ResultData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val localStorage: LocalStorage,
    private val authApi: AuthApi
) {
    fun isFirstLaunch(): Boolean = localStorage.isFirstLaunch

//    fun isNotSinged(): Boolean = true

    fun isSignedIn(): Boolean = localStorage.isSignedIn

    fun disableFirstLaunch() {
        localStorage.isFirstLaunch = false
    }

    fun login(phone: String, password: String): Flow<ResultData<SignInResponse?>> = flow {
        val request = SignInRequest(phone, password)
        val response = authApi.signIn(request)

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

    fun register(request: SignUpRequest): Flow<ResultData<SignUpResponse?>> = flow {
        val response = authApi.signUp(request)
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

    fun verifySignUp(request: SignUpVerifyRequest): Flow<ResultData<SignUpVerifyResponse>> = flow {
        val responseVerify = authApi.signUpVerify(request)
        if (responseVerify.isSuccessful) {
            if (responseVerify.body() != null) {
                emit(ResultData.Success(responseVerify.body()!!))
            } else {
                emit(ResultData.Error(responseVerify.message()))
            }
        } else {
            emit(ResultData.Error(responseVerify.message()))
        }
    }

    fun verifySignIn(request: SignInVerifyRequest): Flow<ResultData<SignInVerifyResponse>> = flow {
        val responseVerify = authApi.signInVerify(request)

        if (responseVerify.isSuccessful) {
            if (responseVerify.body() != null) {
                emit(ResultData.Success(responseVerify.body()!!))
            } else {
                emit(ResultData.Error(responseVerify.message()))
            }
        } else {
            emit(ResultData.Error(responseVerify.message()))
        }
    }

}