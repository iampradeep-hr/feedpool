package com.pradeep.feedpool.ui

import androidx.lifecycle.ViewModel
import com.pradeep.feedpool.repository.NewsRepository

class NewsViewModel (
    val newsRepository: NewsRepository
        ):ViewModel() {
}