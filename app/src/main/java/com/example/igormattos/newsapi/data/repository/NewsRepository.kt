package com.example.igormattos.newsapi.data.repository

import com.example.igormattos.newsapi.data.api.NewApiService
import com.example.igormattos.newsapi.data.model.NewsModel
import com.example.igormattos.newsapi.utils.Constants.Constants

class NewsRepository(private val service: NewApiService) {

    suspend fun getNews(category: String): NewsModel? {
        val response = service.getNews(category)
        if (response.code() == 200) {
            return response.body()
        }
        return null
    }

    suspend fun getSearch(query: String): NewsModel? {
        val response = service.getSeach(query, Constants.SOURCES.sortBy)
        if (response.code() == 200) {
            return response.body()
        }
        return null
    }
}