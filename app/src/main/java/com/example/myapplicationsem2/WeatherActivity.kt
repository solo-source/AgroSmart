package com.example.myapplicationsem2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationsem2.databinding.ActivityWeatherBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fetch weather data
        fetchWeatherData()

        val bottomNavigationView: BottomNavigationView = binding.bottomNavView
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Handle home action - Reload current activity
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    true
                }
                R.id.nav_profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_settings -> {
                    val intent = Intent(this, SettingsActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun fetchWeatherData() {
        val apiKey = "1e8ec065460fe366dac1eb2948e53acc"
        val city = "Navi Mumbai"
        val url = "http://api.openweathermap.org/data/2.5/forcast?q=$city&appid=$apiKey"

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val connection = URL(url).openConnection() as HttpURLConnection
                val response = StringBuilder()
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    response.append(line)
                }
                reader.close()

                val jsonResponse = JSONObject(response.toString())

                // Parse weather data
                val temperature = jsonResponse.getJSONObject("main").getDouble("temp")
                val weatherDescription =
                    jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description")
                val humidity = jsonResponse.getJSONObject("main").getInt("humidity")
                val windSpeed = jsonResponse.getJSONObject("wind").getDouble("speed")

                // Update UI with weather data
                runOnUiThread {
                    updateWeatherUI(temperature, weatherDescription, humidity, windSpeed)
                }
            } catch (e: Exception) {
                Log.e("WeatherActivity", "Error fetching weather data: ${e.message}")
                runOnUiThread {
                    Toast.makeText(this@WeatherActivity, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun updateWeatherUI(temperature: Double, description: String, humidity: Int, windSpeed: Double) {
        // Display temperature in Celsius
        val tempCelsius = temperature - 273.15

        // Format wind speed to display in km/h
        val windSpeedKmh = windSpeed * 3.6

        // Update UI with weather data
        binding.temperature.text = "${String.format("%.1f", tempCelsius)}°C"
        binding.weatherDescription.text = description.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }
        binding.humidity.text = "Humidity: $humidity%"
        binding.windSpeed.text = "Wind Speed: ${String.format("%.1f", windSpeedKmh)} km/h"

        // Optionally, you can also display last updated time
        val sdf = SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault())
        val currentDate = sdf.format(Date())
        binding.forecastDetails.text = "Last Updated: $currentDate"
    }
}
