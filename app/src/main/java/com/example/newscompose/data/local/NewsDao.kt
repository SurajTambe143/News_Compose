package com.example.newscompose.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newscompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete()
    suspend fun delete(article: Article)

    @Query("SELECT*FROM Article")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT*FROM Article WHERE url=:url")
    suspend fun getArticle(url:String): Article?
}