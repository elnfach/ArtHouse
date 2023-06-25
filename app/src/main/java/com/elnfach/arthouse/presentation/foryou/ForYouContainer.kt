package com.elnfach.arthouse.presentation.foryou

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import coil.compose.AsyncImage
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.navigation.Router
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.androidx.compose.koinViewModel


@Composable
fun ForYouContainer(
    externalRouter: Router,
    lifecycleOwner: LifecycleOwner,
    viewModel: ForYouScreenViewModel = koinViewModel()
) {
    Log.e("TESTING", "Im here")
    val state = viewModel.state.value
    Log.e("TESTING", state.isLoading.toString())

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.newsArticles) { item ->
                AsyncImage(model = item?.image, contentDescription = null)
                item?.title?.let { Text(text = it) }
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
            Log.e("TESTING", state.isLoading.toString())
        }
    }
}
