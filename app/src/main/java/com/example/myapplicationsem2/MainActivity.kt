package com.example.myapplicationsem2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationsem2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Search bar
        binding.searchBar.setOnClickListener {
            // Implement search functionality here
        }

        // Setup Weather widget
        binding.btnWeather.setOnClickListener {
            val intent = Intent("com.example.myapplicationsem2.SHOW_WEATHER")
            startActivity(intent)
        }

        // Setup Crop Info button
        binding.btnCropInfo.setOnClickListener {
            val intent = Intent("com.example.myapplicationsem2.SHOW_CROP_INFO")
            startActivity(intent)
        }

        // Setup Market Prices button
        binding.btnMarketPrices.setOnClickListener {
            val intent = Intent(this, MarketPricesActivity::class.java)
            startActivity(intent)
        }

        // Setup Forum button
        binding.btnForum.setOnClickListener {
            val intent = Intent(this, ForumActivity::class.java)
            startActivity(intent)
        }

        // Setup Disease button
        binding.btnDiseaseDiagnosis.setOnClickListener {
            val intent = Intent(this, DiseaseActivity::class.java)
            startActivity(intent)
        }

        // Setup Notifications button
        binding.btnNotifications.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        // Setup Bottom Navigation
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
}
