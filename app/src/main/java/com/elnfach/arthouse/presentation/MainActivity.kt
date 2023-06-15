package com.elnfach.arthouse.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.elnfach.arthouse.presentation.screens.SplashScreen
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
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "splash_screen")
            {
                composable("splash_screen")
                {
                    SplashScreen(navController)
                }
                composable("main_screen")
                {
                    ArtHouseTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            Scaffold {
                                LazyColumn(
                                    Modifier
                                        .padding(it)
                                        .fillMaxSize())
                                {
                                    itemsIndexed(newsArticlesList)
                                    { _, item ->
                                        Box(modifier = Modifier.padding(4.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable {  }) {
                                            Column(modifier = Modifier.padding(10.dp))
                                            {
                                                Image(painter = painterResource(id = item.image),
                                                    contentDescription = item.title,
                                                    modifier = Modifier.clip(RoundedCornerShape(12.dp)))
                                                Spacer(modifier = Modifier.height(4.dp))
                                                Text(text = item.title, fontSize = 20.sp)
                                                Spacer(modifier = Modifier.height(1.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}