package com.example.myapplicationsem2

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationsem2.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding
    private lateinit var dbHelper: SettingsDbHelper
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = SettingsDbHelper(this)
        database = dbHelper.writableDatabase

        binding.backButton.setOnClickListener {
            finish()
        }

        setupLanguageSpinner()
        loadSettings()

        binding.notificationsSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveSetting(SettingsContract.SettingsEntry.COLUMN_NOTIFICATIONS, if (isChecked) 1 else 0)
        }

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            saveSetting(SettingsContract.SettingsEntry.COLUMN_DARK_MODE, if (isChecked) 1 else 0)
        }

        binding.languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                saveSetting(SettingsContract.SettingsEntry.COLUMN_LANGUAGE, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }
    }

    private fun setupLanguageSpinner() {
        val languages = resources.getStringArray(R.array.language_options)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.languageSpinner.adapter = adapter
    }

    private fun loadSettings() {
        val cursor: Cursor = database.query(
            SettingsContract.SettingsEntry.TABLE_NAME,
            null, null, null, null, null, null
        )

        if (cursor.moveToFirst()) {
            val notifications = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsContract.SettingsEntry.COLUMN_NOTIFICATIONS))
            val darkMode = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsContract.SettingsEntry.COLUMN_DARK_MODE))
            val language = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsContract.SettingsEntry.COLUMN_LANGUAGE))

            binding.notificationsSwitch.isChecked = notifications == 1
            binding.darkModeSwitch.isChecked = darkMode == 1
            binding.languageSpinner.setSelection(language)
        }
        cursor.close()
    }

    private fun saveSetting(columnName: String, value: Int) {
        val values = ContentValues().apply {
            put(columnName, value)
        }

        val rowsUpdated = database.update(SettingsContract.SettingsEntry.TABLE_NAME, values, null, null)
        if (rowsUpdated == 0) {
            database.insert(SettingsContract.SettingsEntry.TABLE_NAME, null, values)
        }
    }
}
