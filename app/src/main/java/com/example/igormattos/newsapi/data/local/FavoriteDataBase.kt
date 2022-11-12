package com.example.igormattos.newsapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.igormattos.newsapi.data.model.NewsDB

@Database(
    entities = [NewsDB::class],
    version = 1
)
abstract class FavoriteDataBase : RoomDatabase() {
    abstract fun getFavoriteDao(): FavoriteDao
}