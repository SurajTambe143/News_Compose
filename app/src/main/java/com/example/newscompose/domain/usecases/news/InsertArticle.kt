package com.example.newscompose.domain.usecases.news

import android.provider.SyncStateContract.Helpers.insert
import com.example.newscompose.data.local.NewsDao
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository

class InsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.insertArticle(article)
    }
}