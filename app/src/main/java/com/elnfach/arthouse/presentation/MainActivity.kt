package com.elnfach.arthouse.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.elnfach.arthouse.data.repository.storage.KEY_THEME_SETTING
import com.elnfach.arthouse.data.repository.storage.SHARED_PREFS_THEME_SETTING
import com.elnfach.arthouse.presentation.create.newsArticle.CreateNewArticle
import com.elnfach.arthouse.presentation.foryou.newsArticlePage.NewsArticlePageScreen
import com.elnfach.arthouse.presentation.foryou.newsArticlePage.report.ReportPageScreen
import com.elnfach.arthouse.presentation.main.MainScreen
import com.elnfach.arthouse.presentation.profile.components.ProfileDetail
import com.elnfach.arthouse.presentation.profile.detailsPage.DetailsPageScreen
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
    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

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
                    if ( !isOnline(context = this) ){
                        Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
                        this.finish()
                    }
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
                                    externalRouter = createExternalRouter { screen, params ->
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
                        composable("${Screen.Report.route}/{documentId}", arguments = listOf(
                            navArgument(name = "documentId") {
                                type = NavType.StringType
                                defaultValue = "null"
                                nullable = false
                            }
                        ))
                        {entry->
                            PresentModal {
                                ReportPageScreen(
                                    externalRouter = createExternalRouter { screen, params ->
                                        navController.navigate(screen, params) },
                                    navController = navController,
                                    documentId = entry.arguments?.getString("documentId").toString())
                            }
                        }

                        composable("${Screen.ProfileDetail.route}/{profileDetail}", arguments = listOf(
                            navArgument(name = "profileDetail") {
                                type = NavType.StringType
                                defaultValue = "null"
                                nullable = false
                            }
                        ))
                        {entry->
                            PresentModal {
                                DetailsPageScreen(
                                    externalRouter = createExternalRouter { screen, params ->
                                        navController.navigate(screen, params) },
                                    context = this@MainActivity,
                                    navController = navController,
                                    profileDetail = entry.arguments?.getString("profileDetail").toString()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}