package com.example.igormattos.newsapi.data.local

import androidx.room.*
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.utils.Constants

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save (news: NewsDB)

    @Delete
    suspend fun removeNews(news: NewsDB)

    @Query("DELETE FROM ${Constants.TABLE.NAME} WHERE title=:title")
    suspend fun deleteMovieByTitle(title: String)

    @Query("SELECT * FROM ${Constants.TABLE.NAME}")
    suspend fun getAllFavorites() : List<NewsDB>

    @Query("SELECT EXISTS(SELECT 1 FROM ${Constants.TABLE.NAME} WHERE title = :title)")
    suspend fun favoriteExist(title: String) : Boolean



}