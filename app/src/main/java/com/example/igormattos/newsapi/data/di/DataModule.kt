package com.example.igormattos.newsapi.data.di

import com.example.igormattos.newsapi.data.api.NewApiService
import com.example.igormattos.newsapi.data.repository.NewsRepository
import com.example.igormattos.newsapi.utils.Constants
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load(){
        loadKoinModules(networkModule() + newsModule())
    }

    private fun newsModule() : Module{
        return module {
            single { NewsRepository(get()) }
        }
    }

    private fun networkModule(): Module {
        return module {
            single {
                Retrofit.Builder()
                    .baseUrl(Constants.URL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(NewApiService::class.java)
            }
        }
    }

}