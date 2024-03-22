package com.elnfach.arthouse.presentation.create.newsArticle

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
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

fun TextFieldValue.ofMaxLength(maxLength: Int): TextFieldValue {
    val overLength = text.length - maxLength
    return if (overLength > 0) {
        val headIndex = selection.end - overLength
        val trailIndex = selection.end
        if (headIndex >= 0) {
            copy(
                text = text.substring(0, headIndex) + text.substring(trailIndex, text.length),
                selection = TextRange(headIndex)
            )
        } else {
            copy(text.take(maxLength), selection = TextRange(maxLength))
        }
    } else {
        this
    }
}

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
    var (title, setTitle) = remember {
        mutableStateOf(TextFieldValue())
    }
    var (content, setContent) = remember { mutableStateOf(TextFieldValue()) }
    //

    var isReturned by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var selectedImageUris by remember {
        mutableStateOf<List<Uri?>>(emptyList())
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()) {
        selectedImageUri = it
    }

    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()) {
        selectedImageUris = it
    }

    var storageRef = FirebaseStorage.getInstance().reference.storage

    val maxTitleLength = 255
    val maxContentLength = 2048

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
                                          title = title.text,
                                          createdAt = Timestamp.now(),
                                          content = content.text,
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
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    AsyncImage(modifier = Modifier.height(200.dp).fillMaxWidth(), model = selectedImageUri, contentDescription = null, contentScale = ContentScale.Crop)
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
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = title,
                        onValueChange = { setTitle(it.ofMaxLength(maxTitleLength)) },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        supportingText = {
                            Text(
                                text = "${title.text.length} / $maxTitleLength",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                            )
                        },
                        label = { Text(text = stringResource(id = R.string.enter_article_title))
                        },
                        colors = TextFieldDefaults.colors(
                            errorContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )
                }
                item {
                    Spacer(modifier = Modifier.padding(24.dp))
                }
                item {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = content,
                        onValueChange = { setContent(it.ofMaxLength(maxContentLength)) },
                        label = { Text(text = stringResource(id = R.string.enter_article_content))
                        },
                        supportingText = {
                            Text(
                                text = "${content.text.length} / $maxContentLength",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End,
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            errorContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedContainerColor = Color.Transparent
                        )
                    )
                }
                item {
                    TextButton(onClick = {
                        multiplePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }) {
                        //Text(text = stringResource(id = R.string.load_image))
                        Text(text = "Загрузить галерею")
                    }
                }
                item {
                    Box(modifier = Modifier.height(200.dp).fillMaxWidth()) {
                        LazyRow(Modifier.fillMaxWidth()) {
                            items(selectedImageUris)
                            {
                                AsyncImage(modifier = Modifier.fillMaxSize(), model = it, contentDescription = null, contentScale = ContentScale.Crop)
                            }
                        }
                    }
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