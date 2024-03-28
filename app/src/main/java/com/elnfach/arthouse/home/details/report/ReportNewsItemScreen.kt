package com.elnfach.arthouse.home.details.report

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.elnfach.arthouse.R
import com.elnfach.arthouse.home.HomeScreenViewModel
import com.elnfach.arthouse.utils.navigation.Router

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportNewsItemScreen(
    externalRouter: Router,
    navController: NavHostController,
    documentId: String,
    viewModel: HomeScreenViewModel = hiltViewModel()
)
{
    val typography = MaterialTheme.typography
    val state = viewModel.newsDetailsState.value
    var isReturned by remember { mutableStateOf(false) }

    LaunchedEffect(documentId) {
        viewModel.loadNewsItemById(documentId)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.report_problem),
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
                    Text(
                        text = "Материал",
                        fontWeight = FontWeight.Bold,
                        style = typography.titleMedium
                        )
                }
                item {
                    Row {
                        if(state.value != null)
                        {
                            AsyncImage(model = state.value.image, contentDescription = null,
                                modifier = Modifier
                                    .heightIn(max = 35.dp, min = 35.dp)
                                    .clip(shape = MaterialTheme.shapes.medium),
                                contentScale = ContentScale.Crop)
                            Text(text = state.value.title)
                        } else
                            CircularProgressIndicator()
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