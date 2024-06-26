package com.example.myapplicationsem2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationsem2.databinding.ItemForecastBinding
import java.text.SimpleDateFormat
import java.util.*

class ForecastAdapter(private val forecastList: List<WeatherEntry>) :
    RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    inner class ForecastViewHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherEntry: WeatherEntry) {
            val dateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
            val date = Date(weatherEntry.dt * 1000)
            binding.dateTextView.text = dateFormat.format(date)
            binding.temperatureTextView.text = "${weatherEntry.main.temp}°C"
            binding.weatherDescriptionTextView.text = weatherEntry.weather[0].description.capitalize()
            val iconUrl = "http://openweathermap.org/img/wn/${weatherEntry.weather[0].icon}.png"
            Glide.with(binding.root)
                .load(iconUrl)
                .into(binding.weatherIconImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val binding = ItemForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastList[position])
    }

    override fun getItemCount(): Int = forecastList.size
}
