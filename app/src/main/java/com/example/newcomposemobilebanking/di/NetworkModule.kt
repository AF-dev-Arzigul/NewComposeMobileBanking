package com.example.newcomposemobilebanking.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.newcomposemobilebanking.model.local.LocalStorage
import com.example.newcomposemobilebanking.model.remote.api.AuthApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.newcomposemobilebanking.util.insertTokenInterceptor
import com.example.newcomposemobilebanking.util.refreshTokenInterceptor
import javax.inject.Singleton

const val BASE_URL = "http://143.198.48.222:84/v1/mobile-bank/"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun getOkHTTPClient(@ApplicationContext context: Context, storage: LocalStorage): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        .addInterceptor(insertTokenInterceptor(storage))
        .addInterceptor(refreshTokenInterceptor(storage))
        .build()


    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun apiAuth(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
}