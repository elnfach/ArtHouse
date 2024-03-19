package com.elnfach.arthouse.presentation.foryou

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.foryou.components.NewsArticleCardTop
import com.elnfach.arthouse.presentation.foryou.components.PostListDivider
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ForYouScreen(
    externalRouter: Router,
    navController: NavController,
    lifecycleOwner: LifecycleOwner,
    viewModel: ForYouScreenViewModel = koinViewModel()
) {
    val ctx = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    val state = viewModel.state.value

    // Refresh
    val isRefreshing by remember { mutableStateOf(false) }
    val stateRefresh= rememberPullRefreshState(isRefreshing, {viewModel.pullToRefresh()})
    // ^^^ Refresh ^^^

    // FAB
    val listState = rememberLazyListState()
    val isExpanded by remember { derivedStateOf { listState.firstVisibleItemScrollOffset == 0 } }
    // ^^^ FAB ^^^

    Scaffold(
        topBar = { MediumTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.news),
                    fontSize = 30.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_help_outline_24), contentDescription = null)
                }
            })
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { externalRouter.routeTo(Screen.CreateNewArticle.route) },
                icon = { Icon(Icons.Filled.Edit, stringResource(id = R.string.write_a_article)) },
                text = { Text(text = stringResource(id = R.string.write_a_article)) },
                expanded = isExpanded
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it)
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .pullRefresh(stateRefresh),
                state = listState
            ) {
                items(state.newsArticles) { item ->
                    if (item != null) {
                        NewsArticleCardTop(
                            newsArticle = item,
                            modifier = Modifier.clickable(onClick = {
                                externalRouter.routeTo("${Screen.NewsArticle.route}/${item.id}")
                            }))
                        PostListDivider()
                    }
                }
            }
            Log.e("TESTING | ERROR", state.error)
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
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            PullRefreshIndicator(isRefreshing, stateRefresh, Modifier.align(Alignment.TopCenter))
        }
    }
}