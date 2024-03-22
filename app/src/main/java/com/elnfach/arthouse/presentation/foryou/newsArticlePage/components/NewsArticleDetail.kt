package com.elnfach.arthouse.presentation.foryou.newsArticlePage.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnfach.arthouse.model.NewsArticle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NewsArticleDetail(newsArticle: NewsArticle)
{
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        val sdf = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())
        val formattedDate = sdf.format(Date(newsArticle.createdAt.seconds * 1000))
        Text(
            text = formattedDate,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(bottom = 8.dp)
        )
        Text(
            text = newsArticle.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))
        AsyncImage(
            model = newsArticle.image,
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = newsArticle.content,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Box(modifier = Modifier.fillMaxWidth()) {
            LazyRow(Modifier.fillMaxSize()) {
                items(newsArticle.gallery)
                {image ->
                    AsyncImage(modifier = Modifier.fillMaxSize(), model = image, contentDescription = null, contentScale = ContentScale.Crop)
                    Spacer(modifier = Modifier.padding(24.dp))
                }
            }
        }
    }
}