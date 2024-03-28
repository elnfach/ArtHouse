package com.elnfach.arthouse.create.newsItem

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.elnfach.arthouse.R
import com.elnfach.arthouse.create.newsItem.components.AsyncImage
import com.elnfach.arthouse.create.newsItem.components.TextFieldTemplate
import com.elnfach.arthouse.create.newsItem.components.ValidationUploadNewsItem
import com.elnfach.arthouse.utils.navigation.Router
import com.elnfach.model.NewsItem
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewArticle(
    router: Router,
    context: Context,
    navController: NavHostController,
    viewModel: CreateNewsItemScreenViewModel = hiltViewModel()
)
{
    val state = viewModel.state.value

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedImageUris by remember { mutableStateOf<List<Uri?>>(emptyList()) }

    val (title, setTitle) = remember { mutableStateOf(TextFieldValue()) }
    val (content, setContent) = remember { mutableStateOf(TextFieldValue()) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()) {
        selectedImageUri = it
    }
    val multiplePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia()) {
        selectedImageUris = it
    }

    var isReturned by remember { mutableStateOf(false) }
    var isClicked by remember { mutableStateOf(false) }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val stateConfig = State.Loading(
        stringResource(id = R.string.loading_message),
        ProgressIndicator.Linear()
    )

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
                            isClicked = true
                        }
                    }) {
                        Text(text = stringResource(id = R.string.publish))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {
                        // TODO: Add: Show a warning if the user has entered any data
                        if(!isReturned)
                        {
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
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding->
        Box(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 24.dp)
        ) {
            if(state.loading)
            {
                StateDialog(
                    state = rememberUseCaseState(visible = true, onCloseRequest = {  }),
                    config = StateConfig(state = stateConfig)
                )
            }
            if (isClicked)
            {
                isClicked = false
                ValidationUploadNewsItem(title.text, content.text, selectedImageUri, context)
                {
                    isReturned = true
                    viewModel.addNewsItem(
                        NewsItem(
                            image = selectedImageUri.toString(),
                            title = title.text,
                            content = content.text,
                            gallery = selectedImageUris.map { it.toString() }
                        )
                    ) {
                        if(it)
                        {
                            Toast.makeText(context, "Статья успешно опубликована!", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                        } else
                            Toast.makeText(context, "Произошла ошибка при публикации", Toast.LENGTH_LONG).show()
                    }
                }
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        if(selectedImageUri != null)
                            AsyncImage(uri = selectedImageUri)
                        TextButton(onClick = {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_image_24), contentDescription = stringResource(id = R.string.upload_image))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = stringResource(id = R.string.upload_image))
                        }

                        TextFieldTemplate(255, stringResource(id = R.string.enter_article_title), title, setTitle)
                        Spacer(modifier = Modifier.padding(24.dp))
                        TextFieldTemplate(2048, stringResource(id = R.string.enter_article_content), content, setContent)

                        TextButton(onClick = {
                            multiplePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_image_24), contentDescription = stringResource(id = R.string.upload_gallery))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = stringResource(id = R.string.upload_gallery))
                        }

                        Box(modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()) {
                            LazyRow(Modifier.fillMaxWidth()) {
                                items(selectedImageUris)
                                {
                                    AsyncImage(uri = it)
                                    Spacer(modifier = Modifier.padding(12.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}