package com.elnfach.arthouse.create.newsItem.components

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.elnfach.arthouse.utils.shimmerEffect

@Composable
fun AsyncImage(uri: Uri?)
{
    SubcomposeAsyncImage(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        model = uri, contentDescription = null, contentScale = ContentScale.FillBounds,
        loading = {
            Box(modifier = Modifier
                .fillMaxSize()
                .shimmerEffect())
        })
}