package com.example.newscompose.domain.usecases.news

import com.example.newscompose.data.local.NewsDao
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator  fun invoke(url:String): Article?{
        return newsRepository.selectArticle(url)
    }
}