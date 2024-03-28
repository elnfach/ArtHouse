package com.elnfach.arthouse.home.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.elnfach.arthouse.utils.shimmerEffect

@Composable
fun NewsDetailsScreenSkeleton()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Box(modifier = Modifier
            .height(16.dp)
            .width(50.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .shimmerEffect())
        Spacer(Modifier.height(16.dp))

        Box(modifier = Modifier
            .height(32.dp)
            .width(150.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .shimmerEffect())
        Spacer(Modifier.height(16.dp))

        Box(modifier = Modifier
            .height(180.dp)
            .fillMaxSize()
            .clip(shape = MaterialTheme.shapes.medium)
            .shimmerEffect())
        Spacer(Modifier.height(16.dp))

        Box(modifier = Modifier
            .height(16.dp)
            .width(210.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .shimmerEffect())
        Spacer(Modifier.height(16.dp))
        Box(modifier = Modifier
            .height(16.dp)
            .width(360.dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .shimmerEffect())
        Spacer(Modifier.height(16.dp))
    }
}