package com.example.myapplicationsem2

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationsem2.databinding.ActivityForumBinding

class ForumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityForumBinding
    private lateinit var dbHelper: ForumDbHelper
    private lateinit var database: SQLiteDatabase
    private lateinit var adapter: ForumAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = ForumDbHelper(this)
        database = dbHelper.writableDatabase

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.addPostButton.setOnClickListener {
            addPost()
        }

        setupRecyclerView()
        loadPosts()
    }

    private fun setupRecyclerView() {
        adapter = ForumAdapter()
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.postsRecyclerView.adapter = adapter
    }

    private fun loadPosts() {
        val cursor = database.query(
            ForumContract.ForumEntry.TABLE_NAME,
            null, null, null, null, null, "${ForumContract.ForumEntry.COLUMN_TIMESTAMP} DESC"
        )

        val posts = mutableListOf<Post>()
        while (cursor.moveToNext()) {
            val title = cursor.getString(cursor.getColumnIndexOrThrow(ForumContract.ForumEntry.COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(ForumContract.ForumEntry.COLUMN_CONTENT))
            posts.add(Post(title, content))
        }
        cursor.close()

        adapter.submitList(posts)
    }

    private fun addPost() {
        val title = binding.postTitleEditText.text.toString().trim()
        val content = binding.postContentEditText.text.toString().trim()

        if (title.isNotEmpty() && content.isNotEmpty()) {
            val values = ContentValues().apply {
                put(ForumContract.ForumEntry.COLUMN_TITLE, title)
                put(ForumContract.ForumEntry.COLUMN_CONTENT, content)
            }

            database.insert(ForumContract.ForumEntry.TABLE_NAME, null, values)
            loadPosts()

            binding.postTitleEditText.text.clear()
            binding.postContentEditText.text.clear()
        } else {
            Toast.makeText(this, "Please fill in both title and content.", Toast.LENGTH_SHORT).show()
        }
    }
}
