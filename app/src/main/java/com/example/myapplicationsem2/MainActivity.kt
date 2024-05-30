package com.example.myapplicationsem2

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.gridlayout.widget.GridLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the search bar
        val searchBar: EditText = findViewById(R.id.search_bar)

        // Initialize the weather widget elements
        val weatherIcon: ImageView = findViewById(R.id.weather_icon)
        val weatherTemp: TextView = findViewById(R.id.weather_temp)
        val weatherDescription: TextView = findViewById(R.id.weather_description)

        // Initialize the quick access buttons
        val btnCropInfo: Button = findViewById(R.id.btn_crop_info)
        val btnMarketPrices: Button = findViewById(R.id.btn_market_prices)
        val btnWeather: Button = findViewById(R.id.btn_weather)
        val btnForum: Button = findViewById(R.id.btn_forum)
        val btnDiseaseDiagnosis: Button = findViewById(R.id.btn_disease_diagnosis)
        val btnNotifications: Button = findViewById(R.id.btn_notifications)

        // Handle quick access button clicks
        btnCropInfo.setOnClickListener {
            // Handle crop info button click
        }

        btnMarketPrices.setOnClickListener {
            // Handle market prices button click
        }

        btnWeather.setOnClickListener {
            // Handle weather button click
        }

        btnForum.setOnClickListener {
            // Handle forum button click
        }

        btnDiseaseDiagnosis.setOnClickListener {
            // Handle disease diagnosis button click
        }

        btnNotifications.setOnClickListener {
            // Handle notifications button click
        }

        // Initialize and set up the bottom navigation view
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav_view)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // Handle home navigation
                    true
                }
                R.id.nav_profile -> {
                    // Handle profile navigation
                    true
                }
                R.id.nav_settings -> {
                    // Handle settings navigation
                    true
                }
                else -> false
            }
        }
    }
}
