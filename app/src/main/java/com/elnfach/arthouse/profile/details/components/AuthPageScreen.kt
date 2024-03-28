package com.elnfach.arthouse.profile.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthPageScreen(
    title: String,
    navController: NavController,
    content: @Composable () -> Unit
) {
    Scaffold {
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it))
        {
            content()
        }
    }
}