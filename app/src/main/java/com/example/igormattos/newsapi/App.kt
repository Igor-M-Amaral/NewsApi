package com.example.igormattos.newsapi

import android.app.Application
import com.example.igormattos.newsapi.data.di.DataModule
import com.example.igormattos.newsapi.view.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        PresentationModule.load()
    }
}