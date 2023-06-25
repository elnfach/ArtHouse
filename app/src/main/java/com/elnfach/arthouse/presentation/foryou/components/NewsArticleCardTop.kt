package com.elnfach.arthouse.presentation.foryou.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.ui.theme.ArtHouseTheme
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsArticleCardTop(newsArticle: NewsArticle, modifier: Modifier)
{
    val typography = MaterialTheme.typography
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.medium)
        AsyncImage(
            model = newsArticle.image,
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        newsArticle.title?.let {
            Text(
                text = it,
                style = typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        Text(
            text = "${newsArticle.createdAt?.monthNumber?.let { Month.of(it) }} ${newsArticle.createdAt?.dayOfMonth}",
            style = typography.bodySmall
        )
    }
}