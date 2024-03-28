package com.elnfach.arthouse.main

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.home.HomeScreen
import com.elnfach.arthouse.profile.ProfileScreen
import com.elnfach.arthouse.schedule.ScheduleScreen
import com.elnfach.arthouse.utils.Screen
import com.elnfach.arthouse.utils.navigation.Router

@Composable
fun MainScreen(
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    lifecycleOwner: LifecycleOwner,
    router: Router,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomAppBar {
                viewModel.bottomItems.forEach {
                    NavigationBarItem(
                        selected = currentRoute == it.route,
                        onClick = {
                            if(currentRoute != it.route)
                            {
                                navController.navigate(it.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        label = { Text(text = stringResource(id = it.title), color = MaterialTheme.colorScheme.onSurface) },
                        icon = {
                            if(currentRoute == it.route)
                            {
                                Icon(painter = painterResource(id = it.selectedIcon),
                                    contentDescription = stringResource(id = it.title)
                                )
                            } else {
                                Icon(painter = painterResource(id = it.unselectedIcon),
                                    contentDescription = stringResource(id = it.title)
                                )
                            }
                        }
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it))
        {
            NavHost(navController = navController, startDestination = Screen.ForYou.route)
            {
                composable(Screen.ForYou.route)
                {
                    HomeScreen(externalRouter = router, navController = navController, lifecycleOwner = lifecycleOwner)
                }
                composable(Screen.Schedule.route)
                {
                    ScheduleScreen(externalRouter = router, context = context, lifecycleOwner = lifecycleOwner)
                }
                /*composable(Screen.Messages.route)
                {
                    MessagesScreen(externalRouter = router, navController = navController, lifecycleOwner = lifecycleOwner)
                }*/
                composable(Screen.Profile.route)
                {
                    ProfileScreen(externalRouter = router, context = context, lifecycleScope = lifecycleScope, lifecycleOwner = lifecycleOwner)
                }
            }
        }
    }
}