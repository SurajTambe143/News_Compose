package com.example.newscompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newscompose.data.local.NewsDao
import com.example.newscompose.data.remote.NewsPagingSource
import com.example.newscompose.data.remote.SearchNewsPagingSource
import com.example.newscompose.data.remote.api.NewsApi
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ","),
                    searchQuerry = searchQuery
                )
            }
        ).flow
    }

    override suspend fun insertArticle(article: Article) {
        newsDao.insert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}