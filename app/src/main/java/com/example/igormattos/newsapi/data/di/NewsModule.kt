package com.example.igormattos.newsapi.data.di

import android.app.Application
import androidx.room.Room
import com.example.igormattos.newsapi.data.api.NewApiService
import com.example.igormattos.newsapi.data.local.FavoriteDataBase
import com.example.igormattos.newsapi.data.repository.NewsRepository
import com.example.igormattos.newsapi.utils.Constants.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): NewApiService =
        Retrofit.Builder()
            .baseUrl(Constants.URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewApiService::class.java)

    @Provides
    @Singleton
    fun provideFavoritesDatabase(application: Application) : FavoriteDataBase =
        Room.databaseBuilder(
            application.applicationContext,
            FavoriteDataBase::class.java,
            Constants.TABLE.NAME
        ).build()


    @Provides
    @Singleton
    fun provideNewsRepository(apiService: NewApiService) = NewsRepository(apiService)

    @Provides
    @Singleton
    fun provideFavoriteDao(dataBase: FavoriteDataBase) = dataBase.getFavoriteDao()
}