package com.example.myapplicationsem2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationsem2.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up user data (replace with actual user data)
        val user = User(
            name = "Rakesh Kumar",
            email = "kumar.rakesh@example.com",
            additionalInfo = "I am Rakesh Kumar, a 45 year old farmer from Prayagraj, Uttar Pradesh, India. I manages a 15 acre farm in my village, with a focus on mixed farming and sustainable agriculture. I leverage technology through AgriSmart Pro app for improved efficiency and productivity."
        )
        binding.user = user

        // Set up back button click listener
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        // Set up logout button click listener
        binding.logoutButton.setOnClickListener {
            // Handle logout logic (clear user data, navigate to login screen, etc.)
            handleLogout()
        }
    }

    private fun handleLogout() {
        // Clear user session or any saved user data
        // Navigate back to login screen
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
    data class User(
        val name: String,
        val email: String,
        val additionalInfo: String
    )
}
