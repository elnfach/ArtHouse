package com.elnfach.arthouse.presentation.foryou.newsArticlePage.report

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.elnfach.arthouse.R
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.foryou.ForYouScreenViewModel
import com.elnfach.arthouse.presentation.utils.Screen
import com.elnfach.arthouse.presentation.utils.navigation.Router
import com.maxkeppeker.sheets.core.views.Grid
import org.koin.androidx.compose.koinViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportPageScreen(
    externalRouter: Router,
    navController: NavHostController,
    documentId: String,
    viewModel: ForYouScreenViewModel = koinViewModel()
)
{
    val typography = MaterialTheme.typography
    val state = viewModel.newsArticlePageState.value
    var isReturned by remember { mutableStateOf(false) }

    LaunchedEffect(documentId) {
        viewModel.loadNewsArticleById(documentId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.report_a_problem),
                        maxLines = 1,
                        style = typography.titleMedium,
                        overflow = TextOverflow.Ellipsis)
                },
                navigationIcon = {
                    IconButton(onClick = { if(!isReturned){
                        isReturned = true
                        navController.popBackStack()
                    }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
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
                    Text(
                        text = "Материал",
                        fontWeight = FontWeight.Bold,
                        style = typography.titleMedium
                        )
                }
                item {
                    Row {
                        if(state.newsArticle != null)
                        {
                            AsyncImage(model = state.newsArticle.image, contentDescription = null,
                                modifier = Modifier
                                    .heightIn(max = 35.dp, min = 35.dp)
                                    .clip(shape = MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop)
                            Text(text = state.newsArticle.title)
                        }
                        if(state.isLoading)
                        {
                            CircularProgressIndicator()
                        }
                    }
                }
                item {
                    Text(
                        text = "Опишите проблему",
                        fontWeight = FontWeight.Bold,
                        style = typography.titleMedium
                    )
                }
            }
        }
    }
}