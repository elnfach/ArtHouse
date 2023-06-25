package com.elnfach.arthouse.presentation.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import org.koin.androidx.compose.koinViewModel

data class RadioButtonItem (
    val title: String,
    val theme: Theme
)

data class SettingItem(
    val title: Int,
    val onClick: @Composable () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    router: Router?,
    navController: NavHostController,
    viewModel: SettingsScreenViewModel = koinViewModel()
)
{
    val linkToGithub = "https://github.com/elnfach/ArtHouse"
    val ctx = LocalContext.current

    val radioOptions = listOf(
        RadioButtonItem(stringResource(id = R.string.light_theme), Theme.LIGHT),
        RadioButtonItem(stringResource(id = R.string.dark_theme), Theme.DARK),
        RadioButtonItem(stringResource(id = R.string.system_default), Theme.DEFAULT))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }

    val settingsList = listOf(
        SettingItem(title = R.string.choose_theme) {
            radioOptions.forEach { items ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = items.theme.name == viewModel.theme.value,
                            onClick = {
                                viewModel.onThemeChanged(items.theme)
                                onOptionSelected(items)
                            }
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = items.theme.name == viewModel.theme.value,
                        onClick = {
                            ThemeManager.artHouseTheme = items.theme
                            viewModel.onThemeChanged(items.theme)
                            onOptionSelected(items)
                        })
                    Text(
                        text = items.title,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
        },
        SettingItem(title = R.string.about_app) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(id = R.string.about_app_desc), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(34.dp))
                TextButton(onClick = {
                    val urlIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(linkToGithub)
                    )
                    ctx.startActivity(urlIntent)
                }) {
                    Text(text = stringResource(id = R.string.open_github))
                }
            }
        },
        // SettingItem(title = R.string.whats_new)
    )

    var isClicked by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                navController.popBackStack()
                            }) {
                        Icon(
                            modifier = Modifier.padding(4.dp),
                            imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = stringResource(id = R.string.settings))
                }
            })
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it))
        {
            items(settingsList) {
                    item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isClicked = item.title
                        }) {
                    if (isClicked == item.title)
                    {
                        AlertDialog(
                            title = { Text(text = stringResource(id = item.title)) },
                            text = {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    item.onClick()
                                }
                            },
                            onDismissRequest = { isClicked = 0 },
                            confirmButton = {
                                TextButton(onClick = { isClicked = 0 }) {
                                    Text(text = stringResource(id = R.string.cansel))
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .padding(16.dp),
                        text = stringResource(id = item.title))
                }
            }
        }
    }
}