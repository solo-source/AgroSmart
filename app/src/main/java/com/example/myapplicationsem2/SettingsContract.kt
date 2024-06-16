package com.example.myapplicationsem2

import android.provider.BaseColumns

object SettingsContract {
    object SettingsEntry : BaseColumns {
        const val TABLE_NAME = "settings"
        const val COLUMN_NOTIFICATIONS = "notifications"
        const val COLUMN_DARK_MODE = "dark_mode"
        const val COLUMN_LANGUAGE = "language"
    }
}
