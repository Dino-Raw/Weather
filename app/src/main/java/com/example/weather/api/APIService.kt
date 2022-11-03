package com.example.weather.api

import com.example.weather.model.WeatherModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("v1/forecast.json")
    suspend fun getWeather(
        @Query("key") key: String = "12cdbf51592a4e5a914112046221402",
        @Query("q") param: String?,
    ) : Response<ResponseBody>?
}