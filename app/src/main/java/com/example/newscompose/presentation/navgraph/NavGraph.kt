package com.example.newscompose.presentation.navgraph
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.newscompose.presentation.bookmark.BookmarkScreen
import com.example.newscompose.presentation.bookmark.BookmarkViewModel
import com.example.newscompose.presentation.news_navigator.NewsNavigator
import com.example.newscompose.presentation.onboarding.OnBoardingScreen
import com.example.newscompose.presentation.onboarding.OnBoardingViewModel

@Composable
fun NavGraph(
    startDestination:String
){
    val navController = rememberNavController()
    
    NavHost(navController = navController, startDestination = startDestination){
        navigation(
            route=Route.AppStartNavigation.route,
            startDestination =Route.OnBoardingScreen.route
        ){
            composable(
                route=Route.OnBoardingScreen.route
            ){
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route=Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ){
            composable(
                route=Route.NewsNavigatorScreen.route
            ){
                NewsNavigator()
            }
        }
    }
}