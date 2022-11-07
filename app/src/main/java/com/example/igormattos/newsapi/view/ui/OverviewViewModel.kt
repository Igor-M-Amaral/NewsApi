package com.example.igormattos.newsapi.view.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.newsapi.data.local.FavoriteDao
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.view.OverviewFragmentArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OverviewViewModel(private val favoriteDao: FavoriteDao) : ViewModel() {


    var favorite = MutableLiveData(false)

    fun favoriteNews(newsDetails: OverviewFragmentArgs) {
        viewModelScope.launch(Dispatchers.IO) {
            newsDetails.apply {
                val newsDB = NewsDB(
                    newsDetails.title, newsDetails.author, newsDetails.content, newsDetails.publishedAt, newsDetails.url, newsDetails.urlToImage

                )
                if (favorite.value == true) {
                    favoriteDao.removeNews(newsDB)
                } else {
                    favoriteDao.save(newsDB)
                }
            }
        }
    }

    fun checkFavorite(title: String) {
        viewModelScope.launch(Dispatchers.IO){
            val response = favoriteDao.favoriteExist(title)
            favorite.postValue(response)
        }
    }
}
