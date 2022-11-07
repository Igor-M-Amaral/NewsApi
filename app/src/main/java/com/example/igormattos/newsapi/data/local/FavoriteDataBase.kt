package com.example.igormattos.newsapi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.igormattos.newsapi.data.model.NewsDB

@Database(
    entities = [NewsDB::class],
    version = 1
)

abstract class FavoriteDataBase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {
        private lateinit var INSTANCE: FavoriteDataBase

        fun getDataBase(context: Context): FavoriteDataBase {

            if (!Companion::INSTANCE.isInitialized) {
                synchronized(FavoriteDataBase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(context, FavoriteDataBase::class.java, "favoriteDB")
                            .build()
                }
            }
            return INSTANCE
        }
    }
}