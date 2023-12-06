package com.example.newscompose.data.remote.dto

import com.example.newscompose.domain.model.Article

data class NewsResponse(
    val articles: List<Article>? = null,
    val status: String? = null,
    val totalResults: Int? = null
)