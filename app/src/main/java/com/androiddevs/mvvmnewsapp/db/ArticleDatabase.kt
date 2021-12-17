package com.androiddevs.mvvmnewsapp.db

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.androiddevs.mvvmnewsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase(){
    abstract fun getArticleDao(): ArticleDao

    companion object{
        private var instance: ArticleDatabase? =null
        private val  LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance=it}
        }
        private fun createDatabase(context: Context)=
            Room.databaseBuilder(context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db"
            ).build()
    }
}