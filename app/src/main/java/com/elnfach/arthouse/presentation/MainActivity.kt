package com.elnfach.arthouse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.elnfach.arthouse.presentation.ui.theme.ArtHouseTheme
import com.elnfach.domain.models.NewsArticle
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val vm: MainViewModel by viewModel<MainViewModel>()
    private var newsArticlesList = listOf<NewsArticle>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.newsArticles.observe(this) {
            newsArticlesList = it
        }
        vm.loadNewsArticles()
        setContent {
            ArtHouseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold {
                        LazyColumn(Modifier.padding(it).fillMaxSize())
                        {
                            itemsIndexed(newsArticlesList)
                            { _, item ->
                                Text(text = item.title)
                            }
                        }
                    }
                }
            }
        }
    }
}