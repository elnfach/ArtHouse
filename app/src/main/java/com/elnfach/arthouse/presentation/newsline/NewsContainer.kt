package com.elnfach.arthouse.presentation.newsline

import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleOwner
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.NavigationController
import com.elnfach.arthouse.presentation.utils.navigation.Router

@Composable
fun NewsContainer(
    externalRouter: Router,
    lifecycleOwner: LifecycleOwner
) {
    NavigationController(
        startDestination = Screen.News.route,
        router = externalRouter,
        screens = listOf(
            Pair(Screen.News.route) { nav, router, _ -> NewsScreen(router, nav, lifecycleOwner) }
        )
    )
}