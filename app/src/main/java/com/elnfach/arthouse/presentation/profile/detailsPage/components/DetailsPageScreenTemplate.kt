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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.elnfach.arthouse.R

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