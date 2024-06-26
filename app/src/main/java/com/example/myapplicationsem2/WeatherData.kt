package com.example.myapplicationsem2

data class WeatherData(
    val list: List<WeatherEntry>
)

data class WeatherEntry(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)

data class Main(
    val temp: Double,
    val humidity: Int
)

data class Weather(
    val description: String,
    val icon: String
)

data class Wind(
    val speed: Double
)

