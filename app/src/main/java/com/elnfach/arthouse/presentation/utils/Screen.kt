package com.elnfach.arthouse.presentation.utils

import com.elnfach.arthouse.R

sealed class Screen(
    val route: String,
    val icon: Int,
    val title: Int
)
{
    object Splash: Screen("splash", -1, -1)
    object Main: Screen("main", -1, -1)
    object News: Screen("news", R.drawable.baseline_newspaper_24, R.string.news)
    object Schedule: Screen("schedule", R.drawable.baseline_newspaper_24, R.string.schedule)
    object Menu: Screen("menu", R.drawable.baseline_menu_24, R.string.menu)
    object NewsArticle: Screen("news_article", -1, -1)
}