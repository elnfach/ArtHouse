package com.elnfach.arthouse.presentation.foryou.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnfach.arthouse.R
import com.elnfach.arthouse.model.NewsArticle
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SimpleDateFormat")
@Composable
fun NewsArticleCardTop(newsArticle: NewsArticle, modifier: Modifier)
{
    val typography = MaterialTheme.typography

    val sdf = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())
    val formattedDate = sdf.format(Date(newsArticle.createdAt.seconds * 1000))

    var isClicked by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = newsArticle.image,
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = newsArticle.title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row {
            Text(
                text = "$formattedDate.",
                style = typography.bodySmall
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                IconButton(onClick = {
                    isClicked = true
                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_more_vert_24), contentDescription = null)
                }
            }
        }
    }
    if(isClicked)
    {
        ModalBottomSheet(onDismissRequest = {
            isClicked = false
        }) {
            Box(modifier = Modifier.fillMaxSize())
            {
                Column {

                }
            }
        }
    }
}