package com.elnfach.arthouse.presentation.foryou.components

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Month
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes

@RequiresApi(Build.VERSION_CODES.O)
fun of(month: Int) : String {
    val monthNames = arrayOf(
        "Январь",
        "Февраль",
        "Март",
        "Апрель",
        "Май",
        "Июнь",
        "Июль",
        "Август",
        "Сентябрь",
        "Октябрь",
        "Ноябрь",
        "Декабрь"
    )
    return monthNames[month];
}

@SuppressLint("SimpleDateFormat")
@Composable
fun NewsArticleCardTop(newsArticle: NewsArticle, modifier: Modifier)
{
    val typography = MaterialTheme.typography
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
        val sdf = SimpleDateFormat("dd.MM HH:mm", Locale.getDefault())
        val formattedDate = sdf.format(Date(newsArticle.createdAt.seconds * 1000))
        Text(
            text = "$formattedDate.",
            style = typography.bodySmall
        )
    }
}