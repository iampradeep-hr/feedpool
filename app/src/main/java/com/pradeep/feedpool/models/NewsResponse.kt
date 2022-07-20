package com.pradeep.feedpool.models

import com.pradeep.feedpool.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)