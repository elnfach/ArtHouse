package com.elnfach.arthouse.presentation.profile.detailsPage

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.navigation.NavHostController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.profile.ProfileScreenViewModel
import com.elnfach.arthouse.presentation.profile.components.enums.ProfileDetail
import com.elnfach.arthouse.presentation.profile.detailsPage.components.DetailsPageScreenTemplate
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager
import com.elnfach.arthouse.presentation.utils.navigation.Router
import org.koin.androidx.compose.koinViewModel

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

    val (selectedThemeDesignOptionsOption, onThemeDesignOptionSelected) = remember { mutableStateOf(viewModel.appearanceOptions[2]) }
    val (selectedNotificationsOption, onNotificationsOptionSelected) = remember { mutableStateOf(viewModel.notificationsOptions[2]) }
    val (selectedLanguageOptions, onLanguageOptionsSelected) = remember { mutableStateOf(viewModel.languageOptions[2]) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    when(enum)
    {
        ProfileDetail.NONE -> TODO()
        ProfileDetail.LOGIN -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.login),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Войдите в систему", style = typography.titleLarge, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
                    TextField(value = email, onValueChange = { email = it })
                    TextField(value = password, onValueChange = { password = it })
                    Button(onClick = {
                        viewModel.createUser(email, password)
                    }) {
                        Text(text = "Login")
                    }
                }
            }
        )
        ProfileDetail.PROFILE-> {}
        ProfileDetail.ACTIVATE_SUBSCRIPTION -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.activate_subscription),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null,
                        modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = "Но уже планируем добавить экран для входа", style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
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
                    viewModel.appearanceOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = items.id), modifier = Modifier.padding(horizontal = 8.dp))
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
                    viewModel.notificationsOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = items.id), modifier = Modifier.padding(horizontal = 8.dp))
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
        ProfileDetail.LANGUAGE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.change_language),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)) {
                    viewModel.languageOptions.forEach { items -> Row(Modifier
                        .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = stringResource(id = items.id), modifier = Modifier.padding(horizontal = 8.dp))
                        RadioButton(selected = items.language.name == viewModel.theme.value, onClick = {
                            // TODO: Reminder
//                            ThemeManager.artHouseTheme = items.notifications
//                            viewModel.onThemeChanged(items.notifications)
                            onLanguageOptionsSelected(items)
                        })
                    }
                    }
                }
            }
        )
        ProfileDetail.SHARE_APP -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.share_app),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null,
                        modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = "Но уже планируем добавить экран для входа", style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
        ProfileDetail.PROMOTIONAL_CODE -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.promotional_code),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null,
                        modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = "Но уже планируем добавить экран для входа", style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
        ProfileDetail.FEEDBACK -> DetailsPageScreenTemplate(
            title = stringResource(id = R.string.feedback),
            navController = navController,
            content = {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Icon(painter = painterResource(id = R.drawable.outline_auto_awesome_24), contentDescription = null,
                        modifier = Modifier.scale(2.1F))
                    Spacer(modifier = Modifier.padding(12.dp))
                    Text(text = stringResource(id = R.string.empty_favorite), style = typography.titleLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = "Но уже планируем добавить экран для входа", style = typography.bodyMedium, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                }
            }
        )
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