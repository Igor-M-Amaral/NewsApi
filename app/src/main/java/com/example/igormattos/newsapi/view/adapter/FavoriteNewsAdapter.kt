package com.example.igormattos.newsapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.databinding.ItemPagerNewsBinding
import com.example.igormattos.newsapi.utils.listener.FavoritesListener

class FavoriteNewsAdapter : ListAdapter<NewsDB, FavoriteViewHolder>(NewsDBToCallBack()) {

    private lateinit var listener: FavoritesListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(ItemPagerNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    fun attachListener(favoriteListener: FavoritesListener){
        listener = favoriteListener
    }

    class NewsDBToCallBack: DiffUtil.ItemCallback<NewsDB>(){
        override fun areItemsTheSame(oldItem: NewsDB, newItem: NewsDB): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: NewsDB, newItem: NewsDB): Boolean {
            return oldItem.url == newItem.url
        }

    }
}