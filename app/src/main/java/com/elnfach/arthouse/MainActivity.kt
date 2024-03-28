package com.elnfach.arthouse

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.elnfach.arthouse.create.newsItem.CreateNewArticle
import com.elnfach.arthouse.create.schedule.CreateScheduleScreen
import com.elnfach.arthouse.home.details.NewsArticlePageScreen
import com.elnfach.arthouse.home.details.report.ReportNewsItemScreen
import com.elnfach.arthouse.main.MainScreen
import com.elnfach.arthouse.profile.details.DetailsPageScreen
import com.elnfach.arthouse.profile.details.components.enums.Language
import com.elnfach.arthouse.splash.SplashScreen
import com.elnfach.arthouse.ui.theme.ArtHouseTheme
import com.elnfach.arthouse.ui.theme.Theme
import com.elnfach.arthouse.utils.Screen
import com.elnfach.arthouse.utils.managers.LanguageManager
import com.elnfach.arthouse.utils.managers.ThemeManager
import com.elnfach.arthouse.utils.managers.UserManager
import com.elnfach.arthouse.utils.navigation.PresentNested
import com.elnfach.arthouse.utils.navigation.createExternalRouter
import com.elnfach.arthouse.utils.navigation.navigate
import com.elnfach.storage.KEY_LANGUAGE_SETTING
import com.elnfach.storage.KEY_THEME_SETTING
import com.elnfach.storage.KEY_USER_SETTING
import com.elnfach.storage.SHARED_PREFS_LANGUAGE_SETTING
import com.elnfach.storage.SHARED_PREFS_THEME_SETTING
import com.elnfach.storage.SHARED_PREFS_USER_SETTING
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(ComponentActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val themeSharedPreferences =
            this.getSharedPreferences(SHARED_PREFS_THEME_SETTING, Context.MODE_PRIVATE)
        val theme = themeSharedPreferences.getString(KEY_THEME_SETTING, Theme.DEFAULT.name)
        ThemeManager.artHouseTheme = when(theme)
        {
            Theme.LIGHT.name -> Theme.LIGHT
            Theme.DARK.name -> Theme.DARK
            else -> Theme.DEFAULT
        }

        val languageSharedPreferences =
            this.getSharedPreferences(SHARED_PREFS_LANGUAGE_SETTING, Context.MODE_PRIVATE)
        val language = languageSharedPreferences.getString(KEY_LANGUAGE_SETTING, Language.DEFAULT.name)
        LanguageManager.language = when(language)
        {
            Language.DEFAULT.name -> Language.ENGLISH
            Language.ENGLISH.name -> Language.ENGLISH
            Language.RUSSIAN.name -> Language.RUSSIAN
            else -> Language.DEFAULT
        }

        val locale = Locale(when(language)
        {
            Language.ENGLISH.name -> "en"
            Language.RUSSIAN.name -> "ru"
            else -> Locale.getDefault().language
        })
        val resources = this.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        val userSharedPreferences =
            this.getSharedPreferences(SHARED_PREFS_USER_SETTING, Context.MODE_PRIVATE)
        val user = userSharedPreferences.getString(KEY_USER_SETTING, false.toString())
        UserManager.status = when(user)
        {
            true.toString() -> true
            false.toString() -> false
            else -> false
        }
        Log.e("TTTEEe", "User = $user")

        super.onCreate(savedInstanceState)

        setContent {
            ArtHouseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    /*if ( !isOnline(context = this) ){
                        Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
                        this.finish()
                    }*/
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
                            PresentNested {
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
                            PresentNested {
                                CreateNewArticle(
                                    router = createExternalRouter { screen, params ->
                                        navController.navigate(screen, params) },
                                    context = this@MainActivity,
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
                            PresentNested {
                                ReportNewsItemScreen(
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
                            PresentNested {
                                DetailsPageScreen(
                                    externalRouter = createExternalRouter { screen, params ->
                                        navController.navigate(screen, params) },
                                    context = this@MainActivity,
                                    navController = navController,
                                    profileDetail = entry.arguments?.getString("profileDetail").toString()
                                )
                            }
                        }
                        composable(Screen.AddNewLesson.route)
                        {
                            CreateScheduleScreen(
                                externalRouter = createExternalRouter { screen, params ->
                                    navController.navigate(screen, params) },
                                context = this@MainActivity,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}