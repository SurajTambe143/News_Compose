package com.example.newscompose.presentation.details.screen

import com.example.newscompose.domain.model.Article

sealed class DetailsEvent {

    data class InsertDeleteArticle(val article:Article):DetailsEvent()

    object RemoveSideEffect :DetailsEvent()
}