package com.example.myapplicationsem2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationsem2.databinding.ItemWeatherBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(private val weatherList: List<DailyWeather>) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(private val binding: ItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: DailyWeather) {
            val dateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
            val date = Date(weather.dt * 1000)
            binding.dateTextView.text = dateFormat.format(date)
            binding.tempTextView.text = "${weather.temp.day} °C"

            val iconUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
            Glide.with(binding.weatherIcon.context).load(iconUrl).into(binding.weatherIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount() = weatherList.size
}
