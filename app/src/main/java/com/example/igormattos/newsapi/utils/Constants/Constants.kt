package com.example.igormattos.newsapi.utils.Constants

class Constants private constructor() {

    object URL {
        const val BASE_URL = "https://newsapi.org/v2/"

    }

    //add your api key here
    object APIKEY {
        // example "apiKey=b43aacee2c36d84cdbd0664ee2c44b71"
        const val KEY = "Put your api key here"
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