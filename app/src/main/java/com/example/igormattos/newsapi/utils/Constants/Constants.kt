package com.example.igormattos.newsapi.utils.Constants

class Constants private constructor() {

    object URL {
        const val BASE_URL = "https://newsapi.org/v2/"

    }

    object APIKEY {
        const val KEY = "apiKey=36d84cdbd0664c3eb43aacee2c44b714"
    }

    object SOURCES {
        const val topHeadlines = "top-headlines?"
        const val everything = "everything?"
        const val sortBy = "popularity"
        const val country = "country=br"
    }

    object TABLE{
        const val NAME = "FAVORITE"
    }
}