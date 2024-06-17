package com.example.myapplicationsem2

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${ForumContract.ForumEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${ForumContract.ForumEntry.COLUMN_TITLE} TEXT," +
            "${ForumContract.ForumEntry.COLUMN_CONTENT} TEXT," +
            "${ForumContract.ForumEntry.COLUMN_TIMESTAMP} TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ForumContract.ForumEntry.TABLE_NAME}"

class ForumDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Forum.db"
    }
}
