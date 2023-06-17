package com.elnfach.arthouse.presentation.main

import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.presentation.newsline.NewsContainer
import com.elnfach.arthouse.presentation.schedule.SchedulesScreen
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router

@ExperimentalAnimationApi
@Composable
fun MainScreen(context: Context, lifecycleOwner: LifecycleOwner, router: Router)
{
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val bottomItems = listOf(Screen.News, Screen.Schedule, Screen.Menu)
    Scaffold(
        bottomBar = {
            BottomAppBar {
                bottomItems.forEach {
                    NavigationBarItem(
                        selected = currentRoute == it.route,
                        onClick = {
                            navController.navigate(it.route) {
                                popUpTo = navController.graph.startDestinationId
                                launchSingleTop = true
                            }
                        },
                        label = { Text(text = stringResource(id = it.title)) },
                        icon = {
                            Image(painter = painterResource(id = it.icon),
                                contentDescription = stringResource(id = it.title)
                            )
                        }
                    )
                }
            }
        }
    ) {
        it->
        Box(modifier = Modifier.padding(it))
        {
            NavHost(navController = navController, startDestination = Screen.News.route)
            {
                composable("news")
                {
                    NewsContainer(externalRouter = router, lifecycleOwner)
                }
                composable("schedule")
                {
                    SchedulesScreen(externalRouter = router, context, lifecycleOwner)
                }
                composable("menu")
                {
                    Text(text = "Menu")
                }
            }
        }
    }
}