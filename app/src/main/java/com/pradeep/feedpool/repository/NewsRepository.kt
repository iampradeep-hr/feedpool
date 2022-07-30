package com.pradeep.feedpool.repository

import com.pradeep.feedpool.api.RetrofitInstance
import com.pradeep.feedpool.db.ArticleDatabase

class NewsRepository(
    val db:ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode:String,pageNo:Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNo)
}