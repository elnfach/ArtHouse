package com.elnfach.arthouse.presentation

import android.R
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.elnfach.arthouse.data.repository.storage.KEY_THEME_SETTING
import com.elnfach.arthouse.data.repository.storage.SHARED_PREFS_THEME_SETTING
import com.elnfach.arthouse.presentation.create.newsArticle.CreateNewArticle
import com.elnfach.arthouse.presentation.foryou.ForYouScreenViewModel
import com.elnfach.arthouse.presentation.foryou.NewsArticlePageScreen
import com.elnfach.arthouse.presentation.main.MainScreen
import com.elnfach.arthouse.presentation.settings.SettingsScreen
import com.elnfach.arthouse.presentation.splash.SplashScreen
import com.elnfach.arthouse.presentation.ui.theme.ArtHouseTheme
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.PresentModal
import com.elnfach.arthouse.presentation.utils.navigation.createExternalRouter
import com.elnfach.arthouse.presentation.utils.navigation.navigate


class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences =
            this.getSharedPreferences(SHARED_PREFS_THEME_SETTING, Context.MODE_PRIVATE)
        val theme = sharedPreferences.getString(KEY_THEME_SETTING, "")
        ThemeManager.artHouseTheme = when(theme)
        {
            Theme.LIGHT.name -> Theme.LIGHT
            Theme.DARK.name -> Theme.DARK
            else -> Theme.DEFAULT
        }
        super.onCreate(savedInstanceState)
        setContent {
            ArtHouseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Splash.route) {
                        composable(Screen.Splash.route)
                        {
                            SplashScreen(navController = navController, route = Screen.Main.route)
                        }
                        composable(Screen.Main.route)
                        {
                            MainScreen(
                                context = this@MainActivity,
                                lifecycleScope = lifecycleScope,
                                lifecycleOwner = this@MainActivity,
                                router = createExternalRouter { screen, params ->
                                    navController.navigate(screen, params)
                                })
                        }
                        composable("${Screen.NewsArticle.route}/{documentId}", arguments = listOf(
                            navArgument(name = "documentId") {
                                type = NavType.StringType
                                defaultValue = "null"
                                nullable = false
                            }
                        ))
                        {entry->
                            PresentModal {
                                NewsArticlePageScreen(
                                    documentId = entry.arguments?.getString("documentId").toString(),
                                    router = createExternalRouter { screen, params ->
                                        navController.navigate(screen, params) },
                                    navController = navController,
                                    lifecycleOwner = this@MainActivity)
                            }
                        }
                        composable(Screen.CreateNewArticle.route)
                        {
                            PresentModal {
                                CreateNewArticle(
                                    router = createExternalRouter { screen, params ->
                                        navController.navigate(screen, params) },
                                    context = this@MainActivity,
                                    navController = navController)
                            }
                        }
                        composable(Screen.Settings.route)
                        {
                            PresentModal {
                                SettingsScreen(
                                    router = createExternalRouter { screen, params ->
                                        navController.navigate(screen, params) },
                                    navController = navController)
                            }
                        }
                    }
                }
            }
        }
    }
}