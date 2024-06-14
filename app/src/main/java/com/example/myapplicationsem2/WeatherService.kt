package com.example.myapplicationsem2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<WeatherResponse>

    @GET("onecall")
    fun getPreviousDaysWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric"
    ): Call<OneCallResponse>
}
