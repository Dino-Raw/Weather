package com.example.weather.repository

import com.example.weather.api.APIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object Repository {
    lateinit var retrofit: APIService

    fun isRetrofitInit(): Boolean = ::retrofit.isInitialized

    fun initRetrofit() {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .client(client)
            .build()

        this.retrofit = retrofit.create(APIService::class.java)
    }
}