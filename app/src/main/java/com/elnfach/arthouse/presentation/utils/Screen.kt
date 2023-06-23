package com.elnfach.arthouse.presentation.utils

import com.elnfach.arthouse.R

sealed class Screen(
    val route: String,
    val selectedIcon: Int,
    val unselectedIcon: Int,
    val title: Int
)
{
    object Splash: Screen("splash", -1, -1, -1)
    object Main: Screen("main", -1, -1, -1)
    object ForYou: Screen("news", R.drawable.baseline_upcoming_24, R.drawable.outline_upcoming_24,R.string.news)
    object Schedule: Screen("schedule", R.drawable.baseline_schedule_24, R.drawable.baseline_schedule_24, R.string.schedule)
    object SignIn: Screen("sign_in", -1, -1, -1)
    object Profile: Screen("profile", R.drawable.baseline_person_24, R.drawable.outline_person_24, R.string.profile)
    object Menu: Screen("menu", R.drawable.baseline_menu_24, R.drawable.baseline_menu_24, R.string.menu)
    object Settings: Screen("settings", -1, -1, R.string.settings)
    object NewsArticle: Screen("news_article", -1, -1, -1)
}
