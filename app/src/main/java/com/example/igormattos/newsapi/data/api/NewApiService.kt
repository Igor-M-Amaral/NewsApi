package com.example.igormattos.newsapi.data.api

import com.example.igormattos.newsapi.data.model.NewsModel
import com.example.igormattos.newsapi.utils.Constants.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewApiService {


    @GET(Constants.SOURCES.topHeadlines + Constants.SOURCES.country + "&" + Constants.APIKEY.KEY)
    suspend fun getNews(@Query("category") category: String): Response<NewsModel>


    @GET(Constants.SOURCES.everything + Constants.APIKEY.KEY)
    suspend fun getSeach(@Query("q") query: String, @Query("sortBy") sort: String): Response<NewsModel>

}