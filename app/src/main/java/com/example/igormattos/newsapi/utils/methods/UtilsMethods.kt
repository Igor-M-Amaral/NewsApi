package com.example.igormattos.newsapi.utils.methods

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object UtilsMethods {

    fun convertDate(date: String?): String? {

        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        var convertedDate: Date? = null
        var formattedDate: String? = null

        try {
            convertedDate = dateFormat.parse(date)
            formattedDate = SimpleDateFormat("MMM d, yyyy").format(convertedDate)


        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return formattedDate
    }
}