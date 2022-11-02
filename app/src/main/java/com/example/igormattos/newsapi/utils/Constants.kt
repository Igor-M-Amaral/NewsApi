package com.example.igormattos.newsapi.utils

class Constants private constructor() {

    //top-headlines?country=br&category=entertainment&apiKey=34224b5a549e412eb21ca3c30258f4d5

//    top-headlines?country=br&category={}&apiKey=36d84cdbd0664c3eb43aacee2c44b714

    //endPoint de busca

    // https://newsapi.org/v2/everything?q=ragnarok&apiKey=36d84cdbd0664c3eb43aacee2c44b714

    object URL {
        const val BASE_URL = "https://newsapi.org/v2/"

    }

    object APIKEY {
        const val KEY = "apiKey=36d84cdbd0664c3eb43aacee2c44b714"
    }


    object SOURCES {
        const val business = "category=business"
        const val sports = "category=sports"
        const val technology = "category=technology"
        const val science = "category=science"
        const val entertainment = "category=entertainment"
        const val topHeadlines = "top-headlines?"
        const val everything = "everything?q={}"

        const val source = "category={category}"

        const val country = "country=br"


    }
}