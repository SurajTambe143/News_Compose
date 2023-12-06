package com.example.newscompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newscompose.domain.usecases.app_entry.AppEntryUsecases
import com.example.newscompose.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUsecases: AppEntryUsecases
):ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUsecases.readAppEntry().onEach {shouldStartFromHomeScreen->
            startDestination = if(shouldStartFromHomeScreen){
                Route.NewsNavigation.route
            }else{
                Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition=false

        }.launchIn(viewModelScope)
    }

}