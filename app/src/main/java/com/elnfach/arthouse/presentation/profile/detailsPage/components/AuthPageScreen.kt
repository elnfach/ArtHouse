package com.elnfach.arthouse.presentation.profile.detailsPage.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.elnfach.arthouse.R

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