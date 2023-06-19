package com.elnfach.arthouse.presentation.menu

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.menu.components.AlertDialog
import com.elnfach.arthouse.presentation.settings.SettingsActivity
import com.elnfach.arthouse.presentation.utils.navigation.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    context: Context,
    externalRouter: Router?,
    lifecycleOwner: LifecycleOwner,
) {
    val linkToGithub = "https://github.com/elnfach/ArtHouse"
    val linkToPrivacyPolicy = "https://yandex.ru/search/?text=%D0%BB%D0%B8%D1%86%D0%B5%D0%BD%D0%B7%D0%B8%D0%BE%D0%BD%D0%BD%D0%BE%D0%B5+%D1%81%D0%BE%D0%B3%D0%BB%D0%B0%D1%88%D0%B5%D0%BD%D0%B8%D0%B5+%D1%8D%D1%82%D0%BE&lr=198"
    val linkToTermOfUse = "https://yandex.ru/search/?text=%D1%83%D1%81%D0%BB%D0%BE%D0%B2%D0%B8%D1%8F+%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F+%D1%8D%D1%82%D0%BE&lr=198&src=suggest_B"
    val ctx = LocalContext.current
    var isClicked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.menu)) })
        }
    ) {
        Column(modifier = Modifier
            .padding(horizontal = 12.dp)
            .padding(it)) {
            Spacer(modifier = Modifier.height(16.dp))
            ExtendedFloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = { isClicked = true }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (isClicked)
                    {
                        AlertDialog(
                            icon = {
                                Icon(
                                    Icons.Filled.Info, contentDescription = stringResource(id = R.string.about_app),
                                    tint = MaterialTheme.colorScheme.primary)
                            },
                            title = { Text(text = stringResource(id = R.string.about_app)) },
                            text = {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text(text = stringResource(id = R.string.about_app_desc))
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
                            onDismissRequest = { isClicked = false },
                            confirmButton = {
                                TextButton(onClick = { isClicked = false }) {
                                    Text(text = stringResource(id = R.string.close))
                                }
                            }
                        )
                    }
                    Icon(
                        Icons.Filled.Info, contentDescription = stringResource(id = R.string.about_app),
                        tint = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = stringResource(id = R.string.about_app))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            ExtendedFloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(0.dp),
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    val navigate = Intent(context, SettingsActivity::class.java)
                    ContextCompat.startActivity(context, navigate, Bundle())
                }) {
                Icon(
                    Icons.Filled.Settings, contentDescription = stringResource(id = R.string.settings),
                    tint = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = stringResource(id = R.string.settings))
            }
        }
    }
}