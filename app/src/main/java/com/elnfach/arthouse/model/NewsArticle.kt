package com.elnfach.arthouse.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

data class NewsArticle @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: String? = "",
    val image: String? = "",
    val title: String? = "",
    val createdAt: LocalDate? = LocalDate(),
    val content: String? = ""
)
