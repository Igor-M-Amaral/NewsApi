package com.example.igormattos.newsapi.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.igormattos.newsapi.utils.Constants

@Entity(tableName = Constants.TABLE.NAME)
data class NewsDB(
    @PrimaryKey
    val title: String,
    val author: String,
    val content: String,
    val publishedAt: String,
    val url: String,
    val urlToImage: String

//
//    textTitle.text = args.title
//            textDate.text = args.date
//            textDescription.text = args.title
//            textContent.text = args.content
//            textAuthor.text = args.author


)