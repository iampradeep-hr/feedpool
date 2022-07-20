package com.pradeep.feedpool.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pradeep.feedpool.models.Article


@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(Convertes::class)
abstract class ArticleDatabase:RoomDatabase() {

    abstract fun getArticleDao():ArticleDao
    companion object{
        @Volatile
        private var instance:ArticleDatabase?=null
        private val LOCK=Any()
        operator fun invoke(context: Context)= instance?: synchronized(this){
            //bcz multiple threads shd not create multiple instances, avoid it.
            instance?:createDatabase(context).also {
                instance=it
            }
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()

    }
}