package com.example.myapplicationsem2

import android.provider.BaseColumns

object ForumContract {
    object ForumEntry : BaseColumns {
        const val TABLE_NAME = "forum"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_TIMESTAMP = "timestamp"
    }
}
