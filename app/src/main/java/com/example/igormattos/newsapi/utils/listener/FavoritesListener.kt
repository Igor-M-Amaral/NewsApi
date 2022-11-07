package com.example.igormattos.newsapi.utils.listener

import com.example.igormattos.newsapi.data.model.NewsDB

interface FavoritesListener {

    fun onListClickFavorites(bundle: NewsDB)

    fun onDeleteByTitle(newsDB: NewsDB)
}