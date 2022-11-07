package com.example.igormattos.newsapi.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.databinding.ItemCategoryBinding
import com.example.igormattos.newsapi.databinding.ItemPagerNewsBinding
import com.example.igormattos.newsapi.utils.NewsListener
import com.example.igormattos.newsapi.utils.UtilsMethods

class FavoriteViewHolder(private val binding: ItemPagerNewsBinding, private val listener: NewsListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: NewsDB) {
        binding.textTitle.text = news.title


        Glide.with(itemView.context)
            .load(news.urlToImage)
            .placeholder(R.drawable.place_holder)
            .into(binding.imageThumbnail)

        binding.imageThumbnail.setOnClickListener{
            listener.onListClickFavorites(news)
        }
    }
}