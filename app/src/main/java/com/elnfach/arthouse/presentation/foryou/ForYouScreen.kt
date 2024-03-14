package com.elnfach.arthouse.presentation.foryou

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.elnfach.arthouse.presentation.foryou.components.NewsArticleCardTop
import com.elnfach.arthouse.presentation.utils.navigation.Router
import kotlinx.coroutines.launch
import kotlinx.coroutines.time.delay
import org.koin.androidx.compose.koinViewModel
import java.time.Duration


@OptIn(ExperimentalMaterialApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ForYouScreen(
    externalRouter: Router,
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

    Box(
        modifier = Modifier.fillMaxSize().
        pullRefresh(stateRefresh))
    {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.newsArticles) { item ->
                if (item != null) {
                    NewsArticleCardTop(
                        newsArticle = item,
                        modifier = Modifier.clickable(onClick = {
                            val urlIntent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(item.link)
                            )
                            ctx.startActivity(urlIntent)
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

@Composable
private fun PostListDivider() {
    Divider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
    )
}