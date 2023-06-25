package com.elnfach.arthouse.presentation.profile

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.profile.sign_in.SignInScreen
import com.elnfach.arthouse.presentation.profile.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.profile.signed_in.SignedInScreen
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    context: Context,
    router: Router,
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: ProfileScreenViewModel = koinViewModel()
) {
    // TODO Fix: When changing route and returning back for a fraction of a second, the registration screen appears
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.profile))
                    }
                },
                actions ={
                    IconButton(
                        onClick = { router.routeTo(Screen.Settings.route) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_settings_24),
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Box(Modifier.padding(it)) {
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
                            vm.resetState()
                            navController.navigate(Screen.SignedIn.route)
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
    }
}