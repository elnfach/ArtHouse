package com.elnfach.arthouse.presentation.settings

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager

data class radioButtonItem (
    val title: String,
    val theme: Theme
)

@Composable
fun SettingsScreen(
    context: Context
) {
    var isClicked by remember { mutableStateOf(false) }
    val radioOptions = listOf(
        radioButtonItem(stringResource(id = R.string.light_theme), Theme.LIGHT),
        radioButtonItem(stringResource(id = R.string.dark_theme), Theme.DARK),
        radioButtonItem(stringResource(id = R.string.system_default), Theme.DEFAULT))
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[2]) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
            isClicked = true
        }) {
        if (isClicked)
        {
            AlertDialog(
                title = { Text(text = stringResource(id = R.string.choose_theme)) },
                text = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        radioOptions.forEach {
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .selectable(
                                        selected = (it == selectedOption),
                                        onClick = {
                                            ThemeManager.artHouseTheme = it.theme
                                            onOptionSelected(it)
                                        }
                                    )
                                    .padding(horizontal = 16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = (it == selectedOption),
                                    onClick = {
                                        onOptionSelected(it)
                                        ThemeManager.artHouseTheme = it.theme
                                    })
                                Text(
                                    text = it.title,
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                            }
                        }
                    }
                },
                onDismissRequest = { isClicked = false },
                confirmButton = {
                    TextButton(onClick = { isClicked = false }) {
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
            text = stringResource(id = R.string.choose_theme))
    }
}