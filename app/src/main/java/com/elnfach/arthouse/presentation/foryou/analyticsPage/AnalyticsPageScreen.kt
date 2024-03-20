package com.elnfach.arthouse.presentation.foryou.analyticsPage

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.elnfach.arthouse.presentation.foryou.ForYouScreenViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsPageScreen(
    navController: NavController,
    viewModel: ForYouScreenViewModel = koinViewModel()
) {
    var isReturned by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Отладочная информация") },
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
            })
                 },

    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Text(text = "Количество статей: {КОЛИЧЕСТВО_СТАТЕЙ}")
            }
            item { Text(text = "Количество занятий: {КОЛИЧЕСТВО_ЗАНЯТИЙ}") }
            item { Text(text = "Количество зарегистрированных пользователей: {КОЛИЧЕСТВО_ПОЛЬЗОВАТЕЛЕЙ}") }
            item { Text(text = "Список пользователей: {СПИСОК_ПОЛЬЗОВАТЕЛЕЙ}") }
        }
    }
}