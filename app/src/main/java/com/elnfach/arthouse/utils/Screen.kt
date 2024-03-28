package com.elnfach.arthouse.utils

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
    object ForYou: Screen("news", R.drawable.baseline_home_24, R.drawable.outline_home_24,R.string.home)
    object CreateNewArticle: Screen("create_new_article", -1, -1, R.string.new_article)
    object AddNewLesson: Screen("add_new_lesson", -1, -1, R.string.add_new_lesson)
    object Schedule: Screen("schedule", R.drawable.baseline_access_time_filled_24, R.drawable.baseline_schedule_24, R.string.schedule)
    object SignIn: Screen("sign_in", -1, -1, -1)
    object SignedIn: Screen("signed_in", -1, -1, -1)
    object Profile: Screen("profile", R.drawable.baseline_person_24, R.drawable.outline_person_24, R.string.profile)
    object Menu: Screen("menu", R.drawable.baseline_menu_24, R.drawable.baseline_menu_24, R.string.menu)
    object Settings: Screen("settings", -1, -1, R.string.settings)
    object NewsArticle: Screen("news_article_page", -1, -1, -1)
    object Report: Screen("report_page", -1, -1, R.string.report_problem)
    object ProfileDetail: Screen("profile_detail", -1, -1, -1)
    object Messages: Screen("chat", R.drawable.baseline_message_24, R.drawable.outline_message_24 , R.string.chat)
}
