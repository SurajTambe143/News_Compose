package com.example.newscompose.domain.usecases.news

import com.example.newscompose.data.local.NewsDao
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles (
    private val newsRepository: NewsRepository
) {
    operator  fun invoke():Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}