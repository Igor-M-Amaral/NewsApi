package com.example.igormattos.newsapi.view.di

import com.example.igormattos.newsapi.view.ListViewModel
import com.example.igormattos.newsapi.view.ui.OverviewViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(mainListModule() + overviewModule())
    }

    private fun mainListModule() : Module {
        return module {
            factory { ListViewModel(get(), get()) }
        }
    }

    private fun overviewModule(): Module {
        return module {
            factory { OverviewViewModel(get()) }
        }
    }
}