package com.elnfach.arthouse.presentation.foryou.newsArticlePage

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.presentation.foryou.ForYouScreenViewModel
import com.elnfach.arthouse.presentation.foryou.newsArticlePage.components.NewsArticleDetail
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticlePageScreen(
    externalRouter: Router,
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner,
    documentId: String,
    viewModel: ForYouScreenViewModel = koinViewModel()
) {
    val typography = MaterialTheme.typography
    val state = viewModel.newsArticlePageState.value
    var isReturned by remember { mutableStateOf(false) }

    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(documentId) {
        viewModel.loadNewsArticleById(documentId)
    }

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = {
                if(state.newsArticle != null)
                {
                    Text(text = state.newsArticle.title,
                        maxLines = 1,
                        style = typography.titleMedium,
                        overflow = TextOverflow.Ellipsis)
                }
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
            if(state.newsArticle != null)
            {
                LazyColumn {
                    item {
                        NewsArticleDetail(state.newsArticle)
                    }
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
            if (state.newsArticle == null && state.error.isBlank())
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}