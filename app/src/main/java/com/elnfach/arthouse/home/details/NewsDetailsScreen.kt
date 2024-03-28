package com.elnfach.arthouse.home.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.home.HomeScreenViewModel
import com.elnfach.arthouse.home.details.components.NewsDetailsScreenSkeleton
import com.elnfach.arthouse.home.details.components.NewsItemDetail
import com.elnfach.arthouse.utils.Screen
import com.elnfach.arthouse.utils.SkeletonScreen
import com.elnfach.arthouse.utils.navigation.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticlePageScreen(
    externalRouter: Router,
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    documentId: String,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    val typography = MaterialTheme.typography
    val state = viewModel.newsDetailsState.value

    var isReturned by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(documentId) {
        viewModel.loadNewsItemById(documentId)
    }
    
    SkeletonScreen(isLoading = state.loading,
        skeleton = {
            NewsDetailsScreenSkeleton()
        }
    ) {
        Scaffold(
            topBar = { CenterAlignedTopAppBar(
                title = {
                    Text(text = state.value.title,
                        maxLines = 1,
                        style = typography.titleMedium,
                        overflow = TextOverflow.Ellipsis)
                },
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_more_vert_24), contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(id = R.string.report_problem)) },
                            onClick = {
                                expanded = false
                                externalRouter.routeTo("${Screen.Report.route}/${documentId}")
                            }
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { if(!isReturned){
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
            )
            }
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(it))
            {
                LazyColumn {
                    item {
                        NewsItemDetail(state.value)
                    }
                }
                if(state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}