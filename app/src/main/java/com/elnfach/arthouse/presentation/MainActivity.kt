package com.elnfach.arthouse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.presentation.main.MainScreen
import com.elnfach.arthouse.presentation.splash.SplashScreen
import com.elnfach.arthouse.presentation.ui.theme.ArtHouseTheme
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.createExternalRouter
import com.elnfach.arthouse.presentation.utils.navigation.navigate
import com.elnfach.domain.models.NewsArticle
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModel<MainViewModel>()
    private var newsArticlesList = listOf<NewsArticle>()
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.newsArticles.observe(this) {
            newsArticlesList = it
        }
        vm.loadNewsArticles()
        setContent {
            ArtHouseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Splash.route)
                    {
                        composable(Screen.Splash.route)
                        {
                            SplashScreen(navController = navController, route = Screen.Main.route)
                        }
                        composable(Screen.Main.route)
                        {
                            MainScreen(
                                createExternalRouter { screen, params ->
                                navController.navigate(screen, params)
                            })
                        }
                        composable(Screen.NewsArticle.route)
                        {

                        }
                    }
                }
            }
        }
    }
}