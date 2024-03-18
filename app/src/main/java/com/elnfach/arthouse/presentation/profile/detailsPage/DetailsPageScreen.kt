package com.elnfach.arthouse.presentation.profile.detailsPage

import android.app.Notification
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.profile.ProfileScreenViewModel
import com.elnfach.arthouse.presentation.profile.components.ProfileDetail
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager
import com.elnfach.arthouse.presentation.utils.navigation.Router
import org.koin.androidx.compose.koinViewModel

data class AppearanceRadioButtonItem (
    val title: String,
    val theme: Theme
)

data class NotificationsRadioButtonItem (
    val title: String,
    val notifications: Notifications
)

enum class Notifications {
    OFF, ONLY_IMPORTANT, ALL
}

@Composable
fun DetailsPageScreen(
    externalRouter: Router,
    context: Context,
    navController: NavHostController,
    profileDetail: String,
    viewModel: ProfileScreenViewModel = koinViewModel())
{
    val typography = MaterialTheme.typography

    val enum =  enumValueOf<ProfileDetail>(profileDetail)

    val theme = viewModel.theme.value
    val state = viewModel.state.value

    val appearanceOptions = listOf(
        AppearanceRadioButtonItem(stringResource(id = R.string.system_default), Theme.DEFAULT),
        AppearanceRadioButtonItem(stringResource(id = R.string.light_theme), Theme.LIGHT),
        AppearanceRadioButtonItem(stringResource(id = R.string.dark_theme), Theme.DARK)
    )
    val (selectedThemeDesignOptionsOption, onThemeDesignOptionSelected) = remember { mutableStateOf(appearanceOptions[2]) }

    val notificationsOptions = listOf(
        NotificationsRadioButtonItem(stringResource(id = R.string.notifications_off), Notifications.OFF),
        NotificationsRadioButtonItem(stringResource(id = R.string.notifications_only_important), Notifications.ONLY_IMPORTANT),
        NotificationsRadioButtonItem(stringResource(id = R.string.notifications_all), Notifications.ALL)
    )
    val (selectedNotificationsOption, onNotificationsOptionSelected) = remember { mutableStateOf(notificationsOptions[2]) }

    when(enum)
    {
        ProfileDetail.NONE -> TODO()
        ProfileDetail.LOGIN -> Toast.makeText(context, stringResource(id = R.string.unavailable_feature), Toast.LENGTH_LONG).show()
        ProfileDetail.ACTIVATE_SUBSCRIPTION -> Toast.makeText(context, stringResource(id = R.string.unavailable_feature), Toast.LENGTH_LONG).show()
        ProfileDetail.FAVORITE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.favorite),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(id = R.drawable.baseline_bookmark_border_24), contentDescription = null,
                        modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = stringResource(id = R.string.empty_favorite_desc), style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
        ProfileDetail.APPEARANCE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.appearance),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    Text(modifier = Modifier.padding(bottom = 16.dp), text = stringResource(id = R.string.choose_theme), style = typography.titleLarge, fontWeight = FontWeight.Bold)
                    appearanceOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = items.title, modifier = Modifier.padding(horizontal = 8.dp))
                        RadioButton(selected = items.theme.name == viewModel.theme.value, onClick = {
                            ThemeManager.artHouseTheme = items.theme
                            viewModel.onThemeChanged(items.theme)
                            onThemeDesignOptionSelected(items)
                        })
                    }
                    }
                }
            }
        )
        ProfileDetail.NOTIFICATIONS -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.notifications),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    notificationsOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = items.title, modifier = Modifier.padding(horizontal = 8.dp))
                        RadioButton(selected = items.notifications.name == viewModel.theme.value, onClick = {
                            // TODO: Reminder
//                            ThemeManager.artHouseTheme = items.notifications
//                            viewModel.onThemeChanged(items.notifications)
                            onNotificationsOptionSelected(items)
                        })
                    }
                    }
                }
            }
        )
        ProfileDetail.SHARE_APP -> Toast.makeText(context, stringResource(id = R.string.unavailable_feature), Toast.LENGTH_LONG).show()
        ProfileDetail.PROMOTIONAL_CODE -> Toast.makeText(context, stringResource(id = R.string.unavailable_feature), Toast.LENGTH_LONG).show()
        ProfileDetail.FEEDBACK -> Toast.makeText(context, stringResource(id = R.string.unavailable_feature), Toast.LENGTH_LONG).show()
        ProfileDetail.ABOUT_APP -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.about_app),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.mipmap.ic_launcher_foreground), contentDescription = "Logo",
                        modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.app_name), style = typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text(text = "Версия 1.0 (0)", style = typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(text = "Обновлено 18.03.2024", style = typography.bodyMedium, fontWeight = FontWeight.Normal)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPageScreenTemplate(
    title: String,
    navController: NavController,
    content: @Composable () -> Unit
) {
    var isReturned by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                IconButton(onClick = {
                    if (!isReturned) {
                        isReturned = true
                        navController.popBackStack()
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_arrow_back_ios_24),
                        contentDescription = stringResource(id = R.string.return_back)
                    )
                }
            },
        ) },
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it))
        {
            content()
        }
    }
}