package com.example.igormattos.newsapi.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.igormattos.newsapi.R
import com.example.igormattos.newsapi.data.model.Article
import com.example.igormattos.newsapi.databinding.RowCategoryBinding
import com.example.igormattos.newsapi.utils.UtilsMethods

class CategoryViewHolder(private val binding: RowCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(news: Article) {
        binding.textTitle.text = news.title
        binding.textSourceName.text = news.source.name
        binding.textDate.text = UtilsMethods.convertDate(news.publishedAt)

        Glide.with(itemView.context)
            .load(news.urlToImage)
            .placeholder(R.drawable.place_holder)
            .into(binding.thumbnail)
    }
}