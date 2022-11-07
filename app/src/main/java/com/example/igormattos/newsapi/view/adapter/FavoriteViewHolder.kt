package com.example.igormattos.newsapi.view.adapter

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.databinding.ItemPagerNewsBinding
import com.example.igormattos.newsapi.utils.listener.FavoritesListener
import com.example.igormattos.newsapi.utils.listener.NewsListener

class FavoriteViewHolder(private val binding: ItemPagerNewsBinding, private val listener: FavoritesListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: NewsDB) {
        binding.textTitle.text = news.title


        Glide.with(itemView.context)
            .load(news.urlToImage)
            .placeholder(R.drawable.place_holder)
            .into(binding.imageThumbnail)

        binding.imageThumbnail.setOnClickListener{
            listener.onListClickFavorites(news)
        }
        binding.imageThumbnail.setOnLongClickListener {
            AlertDialog.Builder(itemView.context)
                .setTitle("Delete News")
                .setMessage("Do you want to delete this news?")
                .setPositiveButton("Yes"){dialog, which ->
                    listener.onDeleteByTitle(news)
                }
                .setNeutralButton("Cancel", null)
                .show()

            true
        }
    }
}