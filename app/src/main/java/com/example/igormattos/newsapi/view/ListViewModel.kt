package com.example.igormattos.newsapi.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.igormattos.newsapi.data.model.NewsModel
import com.example.igormattos.newsapi.data.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _newsByCategory = MutableLiveData<NewsModel?>()
    val newsByCategory: LiveData<NewsModel?> = _newsByCategory

    private val _trendingNews = MutableLiveData<NewsModel?>()
    val trendingNews: LiveData<NewsModel?> = _trendingNews

    private val _searchNews = MutableLiveData<NewsModel?>()
    val searchNews: LiveData<NewsModel?> = _searchNews

    val progressBar = MutableLiveData<Boolean>()


    fun load(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val resultTrending = repository.getNews("general")
            _trendingNews.postValue(resultTrending)

            val resultCategory = repository.getNews(category)
            _newsByCategory.postValue(resultCategory)

        }
    }

    fun search(query: String) {
        progressBar.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val resultSearch = repository.getSearch(query)
            _searchNews.postValue(resultSearch)
            progressBar.postValue(false)

        }
    }
}