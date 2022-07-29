package com.pradeep.feedpool.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "articles"
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,

    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source, //Room supports only primitive dataTypes and strings, not custom
    //use type converters
    val title: String,
    val url: String,
    val urlToImage: String
)