package com.example.myapplicationsem2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class User(val username: String, val password: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_USERS = "users"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_USERS (" +
                "$COLUMN_USERNAME TEXT PRIMARY KEY, " +
                "$COLUMN_PASSWORD TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun insertUser(user: User): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_USERNAME, user.username)
            put(COLUMN_PASSWORD, user.password)
        }
        return db.insert(TABLE_USERS, null, values)
    }

    fun getUser(username: String): User? {
        val db = this.readableDatabase
        val cursor: Cursor = db.query(
            TABLE_USERS,
            arrayOf(COLUMN_USERNAME, COLUMN_PASSWORD),
            "$COLUMN_USERNAME = ?",
            arrayOf(username),
            null,
            null,
            null
        )

        return if (cursor.moveToFirst()) {
            val user = User(
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            )
            cursor.close()
            user
        } else {
            cursor.close()
            null
        }
    }
}
