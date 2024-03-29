package com.muen.gamesnake.domain.navigation

sealed class Screen(val route: String) {
    data object Menu : Screen(route = "menu_screen")
    data object HighScores : Screen(route = "high_scores_screen")
    data object Settings : Screen(route = "settings_screen")
    data object About : Screen(route = "about_screen")
}
