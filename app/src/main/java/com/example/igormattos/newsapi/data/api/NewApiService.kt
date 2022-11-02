package com.example.igormattos.newsapi.data.api

import com.example.igormattos.newsapi.data.model.NewsModel
import com.example.igormattos.newsapi.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewApiService {

    @GET(Constants.SOURCES.topHeadlines + Constants.SOURCES.country + "&" + Constants.SOURCES.entertainment + "&" + Constants.APIKEY.KEY)
    suspend fun getEntertainment(): Response<NewsModel>

    @GET(Constants.SOURCES.topHeadlines + Constants.SOURCES.country +"&" + Constants.APIKEY.KEY)
    suspend fun getNews(@Query("category") category: String): Response<NewsModel>


//    @GET("api.php?company_name={name}")
//    open fun getRoms_center(@Query("name") name: String?): Call<Model?>?
//
//    @GET("api.php")
//    open fun getRoms_center(@Query("company_name") name: String?): Call<Model?>?

}