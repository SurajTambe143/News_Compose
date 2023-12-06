package com.example.newscompose.presentation.details.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.model.Article
import com.example.newscompose.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    val newsUseCases: NewsUseCases
):ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.InsertDeleteArticle ->{

                viewModelScope.launch {
                    val article = newsUseCases.selectArticle(event.article.url)
                    if (article==null){
                        insertArticle(event.article)
                    }else{
                        deleteArticle(event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect->{
                sideEffect=null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article)
        sideEffect="Article Deleted"
    }

    private suspend fun insertArticle(article: Article) {
        newsUseCases.insertArticle(article)
        sideEffect="Article Saved"
    }
}