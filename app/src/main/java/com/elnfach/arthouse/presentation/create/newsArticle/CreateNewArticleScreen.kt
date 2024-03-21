package com.elnfach.arthouse.presentation.create.newsArticle

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.elnfach.arthouse.R
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.navigation.Router
import com.google.firebase.Timestamp
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.compose.koinViewModel


@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CreateNewArticle(
    router: Router?,
    context: Context,
    navController: NavHostController,
    viewModel: CreateNewsArticleScreenViewModel = koinViewModel()
)
{
    val typography = MaterialTheme.typography

    val state = viewModel.state.value
    //
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    //

    var isReturned by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()) {
        selectedImageUri = it
    }

    var storageRef = FirebaseStorage.getInstance().reference.storage

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.new_article),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis)
                },
                actions = {
                          TextButton(onClick = {
                              if(!isReturned)
                              {
                                  viewModel.addNewsArticles(
                                      NewsArticle(
                                          image = selectedImageUri.toString(),
                                          title = title,
                                          createdAt = Timestamp.now(),
                                          content = content,
                                          id = "TestID"
                                      )
                                  )
                                  navController.popBackStack()
                              }
                          }) {
                              Text(text = stringResource(id = R.string.publish))
                          }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if(!isReturned)
                        {
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
                scrollBehavior = scrollBehavior
            )
        }
    ) {padding->
        Box(
            modifier = Modifier.padding(padding)
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                item {
                    AsyncImage(model = selectedImageUri, contentDescription = null, contentScale = ContentScale.Crop)
                }
                item {
                    TextButton(onClick = {
                        photoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) {
                        Text(text = stringResource(id = R.string.load_image))
                    }
                }
                item {
                    TextField(value = title,
                        onValueChange = { title = it },
                        label = { Text(text = stringResource(id = R.string.enter_article_title))
                        }
                    )
                }
                item {
                    TextField(value = content,
                        onValueChange = { content = it },
                        label = { Text(text = stringResource(id = R.string.enter_article_title))
                        }
                    )
                }
            }
            if(state.success)
            {
                isReturned = true
                navController.popBackStack()
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
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}