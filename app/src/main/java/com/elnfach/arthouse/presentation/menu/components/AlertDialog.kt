package com.elnfach.arthouse.presentation.menu.components

import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable

@Composable
fun AlertDialog(
    icon: @Composable () -> Unit,
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    onDismissRequest: () -> Unit,
    confirmButton: @Composable () -> Unit,
) {
    AlertDialog(
        icon = { icon() },
        title = { title() },
        text = { text()  },
        onDismissRequest = { onDismissRequest() },
        confirmButton = { confirmButton() }
    )
}