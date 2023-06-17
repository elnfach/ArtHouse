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

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                                this@MainActivity,
                                createExternalRouter { screen, params ->
                                navController.navigate(screen, params)
                            })
                        }
                    }
                }
            }
        }
    }
}