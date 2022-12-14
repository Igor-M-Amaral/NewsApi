package com.example.igormattos.newsapi.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.newsapi.data.local.FavoriteDao
import com.example.igormattos.newsapi.data.local.FavoriteDataBase
import com.example.igormattos.newsapi.data.model.NewsDB
import com.example.igormattos.newsapi.data.model.NewsModel
import com.example.igormattos.newsapi.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: NewsRepository, private val favoriteDao: FavoriteDao) :
    ViewModel() {

    private val _newsByCategory = MutableLiveData<NewsModel?>()
    val newsByCategory: LiveData<NewsModel?> = _newsByCategory

    private val _trendingNews = MutableLiveData<NewsModel?>()
    val trendingNews: LiveData<NewsModel?> = _trendingNews

    private val _searchFromAPI = MutableLiveData<NewsModel?>()
    val searchFromAPI: LiveData<NewsModel?> = _searchFromAPI

    private val _newsDB = MutableLiveData<List<NewsDB>>()
    val newsDB: LiveData<List<NewsDB>> = _newsDB

    val progressBar = MutableLiveData<Boolean>()


    fun loadByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultCategory = repository.getNews(category)
            _newsByCategory.postValue(resultCategory)
        }
    }

    fun loadGeneral(){
        viewModelScope.launch(Dispatchers.IO) {
            val resultTrending = repository.getNews("general")
            _trendingNews.postValue(resultTrending)
        }
    }

    fun searchFromApi(query: String) {
        progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val resultSearch = repository.getSearch(query)
            _searchFromAPI.postValue(resultSearch)
            progressBar.postValue(false)

        }
    }

    fun listFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            _newsDB.postValue(favoriteDao.getAllFavorites())
        }
    }

    fun deleteFavorite(title: NewsDB) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteDao.removeNews(title)
        }
    }

    fun searchFromDB(searchString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _newsDB.postValue(favoriteDao.searchDataBase(searchString))
            } catch (e: NullPointerException) {
                //TODO
            }
        }
    }

}