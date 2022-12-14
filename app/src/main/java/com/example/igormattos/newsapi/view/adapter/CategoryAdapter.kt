package com.example.igormattos.newsapi.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.igormattos.newsapi.data.model.Article
import com.example.igormattos.newsapi.databinding.ItemCategoryBinding
import com.example.igormattos.newsapi.utils.listener.NewsListener


class CategoryAdapter : ListAdapter<Article, CategoryViewHolder>(NewsToCallBack()){

    private lateinit var listener: NewsListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val news = getItem(position)
        holder.bind(news)
    }

    fun attachListener(news: NewsListener){
        listener = news
    }

    class NewsToCallBack: DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }


    }
}