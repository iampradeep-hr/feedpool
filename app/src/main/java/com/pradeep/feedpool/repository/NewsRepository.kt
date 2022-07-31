package com.pradeep.feedpool.repository

import com.pradeep.feedpool.api.RetrofitInstance
import com.pradeep.feedpool.db.ArticleDatabase
import com.pradeep.feedpool.models.Article

class NewsRepository(
    val db:ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode:String,pageNo:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNo)


    suspend fun getSearchNews(searchQuery:String,pageNo: Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNo)

    suspend fun upsert(article:Article)=db.getArticleDao().upsert(article)
    fun getSavedNews()=db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticle(article)
}