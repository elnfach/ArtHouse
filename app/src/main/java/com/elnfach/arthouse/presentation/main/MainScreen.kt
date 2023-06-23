package com.elnfach.arthouse.presentation.main

import android.content.Context
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.presentation.foryou.ForYouContainer
import com.elnfach.arthouse.presentation.profile.ProfileScreen
import com.elnfach.arthouse.presentation.schedule.ScheduleScreen
import com.elnfach.arthouse.presentation.profile.sign_in.SignInScreen
import com.elnfach.arthouse.presentation.profile.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    context: Context,
    lifecycleScope: LifecycleCoroutineScope,
    lifecycleOwner: LifecycleOwner,
    router: Router,
    viewModel: MainScreenViewModel = koinViewModel()
)
{
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
                            navController.navigate(it.route) {
                                popUpTo = navController.graph.startDestinationId
                                launchSingleTop = true
                            }
                        },
                        label = { Text(text = stringResource(id = it.title)) },
                        icon = {
                            if(currentRoute == it.route)
                            {
                                Image(painter = painterResource(id = it.selectedIcon),
                                    contentDescription = stringResource(id = it.title)
                                )
                            } else {
                                Image(painter = painterResource(id = it.unselectedIcon),
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
                    ForYouContainer(externalRouter = router, lifecycleOwner)
                }
                composable(Screen.Schedule.route)
                {
                    ScheduleScreen(externalRouter = router, context, lifecycleOwner)
                }
                composable(Screen.Profile.route)
                {
                    ProfileScreen(context = context, lifecycleScope = lifecycleScope)
                }
            }
        }
    }
}