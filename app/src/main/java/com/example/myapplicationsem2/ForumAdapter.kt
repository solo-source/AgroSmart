package com.example.myapplicationsem2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationsem2.databinding.ItemPostBinding

class ForumAdapter : RecyclerView.Adapter<ForumAdapter.ForumViewHolder>() {

    private val posts = mutableListOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ForumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount() = posts.size

    fun submitList(newPosts: List<Post>) {
        posts.clear()
        posts.addAll(newPosts)
        notifyDataSetChanged()
    }

    class ForumViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.postTitleTextView.text = post.title
            binding.postContentTextView.text = post.content
        }
    }
}
