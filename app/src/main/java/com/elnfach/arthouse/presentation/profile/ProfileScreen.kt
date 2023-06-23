package com.elnfach.arthouse.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.presentation.profile.sign_in.SignInScreen
import com.elnfach.arthouse.presentation.profile.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.profile.signed_in.SignedInScreen
import com.elnfach.arthouse.presentation.utils.Screen
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    // TODO Fix: When changing route and returning back for a fraction of a second, the registration screen appears
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SignIn.route)
    {
        composable(Screen.SignIn.route)
        {
            LaunchedEffect(key1 = Unit) {
                if(viewModel.googleAuthUiClient.getSignedInUser() != null) {
                    navController.navigate(Screen.SignedIn.route)
                }
            }

            val vm = viewModel<SignInViewModel>()
            val state by vm.state.collectAsStateWithLifecycle()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == ComponentActivity.RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = viewModel.googleAuthUiClient.signInWithIntent(
                                intent = result.data ?: return@launch
                            )
                            vm.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        context,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()

                    navController.popBackStack()
                    navController.navigate(Screen.SignedIn.route)
                    vm.resetState()
                }
            }

            SignInScreen(
                state = state,
                onSignInClick = {
                    lifecycleScope.launch {
                        val signInIntentSender = viewModel.googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
            )
        }
        composable(Screen.SignedIn.route)
        {
            SignedInScreen(
                userData = viewModel.googleAuthUiClient.getSignedInUser(),
                onSignOut = {
                    lifecycleScope.launch {
                        viewModel.googleAuthUiClient.signOut()
                        Toast.makeText(
                            context,
                            "Signed out",
                            Toast.LENGTH_LONG
                        ).show()

                        navController.popBackStack()
                    }
                }
            )
        }
    }
}