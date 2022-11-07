package com.example.igormattos.newsapi.utils.listener

import com.example.igormattos.newsapi.data.model.Article
import com.example.igormattos.newsapi.data.model.NewsDB

interface NewsListener {

    fun onListClick(bundle: Article)


}