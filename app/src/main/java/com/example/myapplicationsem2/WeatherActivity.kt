package com.example.myapplicationsem2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplicationsem2.databinding.ActivityWeatherBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private lateinit var weatherService: WeatherService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        // Initialize Retrofit
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherService = retrofit.create(WeatherService::class.java)

        // Fetch current weather data
        fetchCurrentWeather()

        // Fetch previous days weather data
        fetchPreviousDaysWeather()
    }

    private fun fetchCurrentWeather() {
        val apiKey = "1e8ec065460fe366dac1eb2948e53acc"
        val call = weatherService.getCurrentWeather("Mumbai", apiKey)

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                    if (weatherResponse != null) {
                        updateCurrentWeatherUI(weatherResponse)
                    }
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("WeatherActivity", "Failed to fetch current weather: ${t.message}")
            }
        })
    }

    private fun updateCurrentWeatherUI(weatherResponse: WeatherResponse) {
        binding.currentTemperatureTextView.text = "${weatherResponse.main.temp} °C"
        val iconUrl = "https://openweathermap.org/img/wn/${weatherResponse.weather[0].icon}.png"
        Glide.with(this).load(iconUrl).into(binding.currentWeatherIcon)
    }

    private fun fetchPreviousDaysWeather() {
        val apiKey = "1e8ec065460fe366dac1eb2948e53acc"
        val call = weatherService.getPreviousDaysWeather(
            lat = 19.0760, // Latitude for Mumbai
            lon = 72.8777, // Longitude for Mumbai
            exclude = "current,minutely,hourly,alerts",
            apiKey = apiKey
        )

        call.enqueue(object : Callback<OneCallResponse> {
            override fun onResponse(call: Call<OneCallResponse>, response: Response<OneCallResponse>) {
                if (response.isSuccessful) {
                    val oneCallResponse = response.body()
                    if (oneCallResponse != null) {
                        updatePreviousDaysWeatherUI(oneCallResponse.daily)
                    }
                }
            }

            override fun onFailure(call: Call<OneCallResponse>, t: Throwable) {
                Log.e("WeatherActivity", "Failed to fetch previous days weather: ${t.message}")
            }
        })
    }

    private fun updatePreviousDaysWeatherUI(weatherList: List<DailyWeather>) {
        val adapter = WeatherAdapter(weatherList)
        binding.previousDaysRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.previousDaysRecyclerView.adapter = adapter
    }
}
