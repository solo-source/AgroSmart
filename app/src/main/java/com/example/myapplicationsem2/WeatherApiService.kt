package com.example.myapplicationsem2

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String
    ): WeatherData
}

