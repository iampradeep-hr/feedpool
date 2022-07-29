package com.pradeep.feedpool.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pradeep.feedpool.db.ArticleDatabase
import com.pradeep.feedpool.repository.NewsRepository


class NewsViewModelProviderFactory(private val newsRepository: ArticleDatabase): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T

    }

}