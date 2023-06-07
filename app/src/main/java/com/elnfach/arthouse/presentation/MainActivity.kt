package com.elnfach.arthouse.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.elnfach.arthouse.presentation.ui.theme.ArtHouseTheme
import com.elnfach.domain.models.NewsArticle

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    private var newsArticlesList = listOf<NewsArticle>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, MainViewModelFactory()).get(MainViewModel::class.java)
        viewModel.newsArticles.observe(this, Observer{
            newsArticlesList = it
        })
        viewModel.loadNewsArticles()
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
                            {index, item ->
                                Text(text = item.title)
                            }
                        }
                    }
                }
            }
        }
    }
}