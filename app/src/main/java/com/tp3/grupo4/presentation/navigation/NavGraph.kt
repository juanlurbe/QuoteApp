package com.tp3.grupo4.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tp3.grupo4.presentation.screen.FavoriteQuotesScreen
import com.tp3.grupo4.presentation.screen.QuoteScreen

object Routes {
    const val QUOTES = "quotes"
    const val FAVORITES = "favorites"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.QUOTES) {
        composable(Routes.QUOTES) {
            QuoteScreen(navController = navController)
        }
        composable(Routes.FAVORITES) { FavoriteQuotesScreen(navController = navController) }
    }
}