package com.example.myapplicationsem2

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplicationsem2.databinding.ActivityWeatherBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private val apiKey = "1e8ec065460fe366dac1eb2948e53acc"
    private val latitude = 19.0330 // Example latitude
    private val longitude = 73.0297 // Example longitude

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.forecastRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchWeatherData()
    }

    private fun fetchWeatherData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getWeatherData(
                    latitude, longitude, "minutely,hourly", apiKey, "metric"
                )
                withContext(Dispatchers.Main) {
                    displayWeatherData(response)
                }
            } catch (e: HttpException) {
                Log.e("WeatherActivity", "HttpException: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@WeatherActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e("WeatherActivity", "Exception: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@WeatherActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun displayWeatherData(weatherData: WeatherData) {
        // Display current weather data
        val currentWeather = weatherData.list[0]
        binding.weatherDescriptionTextView.text = currentWeather.weather[0].description.capitalize()
        binding.temperatureTextView.text = "${currentWeather.main.temp}°C"
        binding.humidityTextView.text = "Humidity: ${currentWeather.main.humidity}%"
        binding.windSpeedTextView.text = "Wind Speed: ${currentWeather.wind.speed} m/s"
        val iconUrl = "http://openweathermap.org/img/wn/${currentWeather.weather[0].icon}.png"
        Glide.with(this)
            .load(iconUrl)
            .into(binding.weatherIconImageView)

        // Display 5-day forecast
        val forecastAdapter = ForecastAdapter(weatherData.list.take(5))
        binding.forecastRecyclerView.adapter = forecastAdapter
    }
}

