package com.elnfach.arthouse.presentation.newsline

import android.content.Intent
import android.net.Uri
import android.os.Parcelable
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.elnfach.arthouse.presentation.utils.navigation.Router
import com.elnfach.domain.models.NewsArticle
import kotlinx.parcelize.Parcelize
import org.koin.androidx.compose.koinViewModel

@Parcelize
data class NewsArticleModel(
    val id: Int,
    val image: Int,
    val title: String,
    val content: String
): Parcelable
{
    companion object Keys {
        const val NewsArticle = "item"
    }
}

@Composable
fun NewsScreen(
    router: Router?,
    navController: NavController,
    lifecycleOwner: LifecycleOwner,
    viewModel: NewsLineViewModel = koinViewModel()
)
{
    val ctx = LocalContext.current
    var newsArticlesList = listOf<NewsArticle>()
    viewModel.newsArticles.observe(lifecycleOwner) {
        newsArticlesList = it
    }
    viewModel.loadNewsArticles()

    Scaffold {
        LazyColumn(modifier = Modifier
            .padding(it)
            .fillMaxSize())
        {
            itemsIndexed(newsArticlesList)
            { _, item ->
                Box(modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .clickable {
                        val urlIntent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(item.content)
                        )
                        ctx.startActivity(urlIntent)
                    }) {
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
