package com.example.myapplicationsem2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${SettingsContract.SettingsEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${SettingsContract.SettingsEntry.COLUMN_NOTIFICATIONS} INTEGER," +
            "${SettingsContract.SettingsEntry.COLUMN_DARK_MODE} INTEGER," +
            "${SettingsContract.SettingsEntry.COLUMN_LANGUAGE} INTEGER)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${SettingsContract.SettingsEntry.TABLE_NAME}"

class SettingsDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Settings.db"
    }
}
