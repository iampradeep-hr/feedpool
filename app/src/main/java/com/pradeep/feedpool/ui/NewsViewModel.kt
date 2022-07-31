package com.pradeep.feedpool.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.pradeep.feedpool.models.NewsResponse
import com.pradeep.feedpool.repository.NewsRepository
import com.pradeep.feedpool.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel (val newsRepository: NewsRepository):ViewModel() {

    val breakingNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage=1

    val searchNews:MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage=1
    init {
        getBreakingNews("in")
    }
    fun getBreakingNews(countryCode:String)=viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        val response=newsRepository.getBreakingNews(countryCode,breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun getSearchNews(searchQuery: String)=viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response=newsRepository.getSearchNews(searchQuery,searchNewsPage)
        searchNews.postValue(handleSearchNewsResponse(response))
    }

    private fun handleBreakingNewsResponse( response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message().toString())
    }

    private fun handleSearchNewsResponse( response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message().toString())
    }



}