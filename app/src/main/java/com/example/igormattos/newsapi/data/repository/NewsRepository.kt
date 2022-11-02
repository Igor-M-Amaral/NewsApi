package com.example.igormattos.newsapi.data.repository

import com.example.igormattos.newsapi.data.api.NewApiService
import com.example.igormattos.newsapi.data.model.NewsModel

class NewsRepository(private val service: NewApiService) {

    suspend fun getNews(category: String): NewsModel? {
        val response = service.getNews(category)
        if (response.code() == 200){
            return response.body()
        }
        return null
    }
}