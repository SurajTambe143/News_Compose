package com.example.newscompose.domain.usecases.news

import com.example.newscompose.data.local.NewsDao
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository
import java.nio.file.Files.delete

class DeleteArticle (
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}