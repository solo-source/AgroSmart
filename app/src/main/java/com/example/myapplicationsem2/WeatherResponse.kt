package com.example.myapplicationsem2

data class WeatherResponse(
    val main: Main,
    val weather: List<Weather>,
    val coord: Coord
)

data class Main(
    val temp: Double
)

data class Weather(
    val icon: String
)

data class Coord(
    val lat: Double,
    val lon: Double
)

