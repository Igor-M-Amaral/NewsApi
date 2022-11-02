package com.example.igormattos.newsapi.utils

import com.example.igormattos.newsapi.data.model.Article

interface NewsListener {

    fun onListClick(bundle: Article)
}