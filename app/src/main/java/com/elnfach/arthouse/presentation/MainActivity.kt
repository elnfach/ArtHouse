package com.elnfach.arthouse.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.presentation.main.MainScreen
import com.elnfach.arthouse.presentation.profile.ProfileScreen
import com.elnfach.arthouse.presentation.sign_in.SignInScreen
import com.elnfach.arthouse.presentation.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.theme.ui.ArtHouseTheme
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.createExternalRouter
import com.elnfach.arthouse.presentation.utils.navigation.navigate
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtHouseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screen.Main.route) {
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
                    }
                }
            }
        }
    }
}