package com.elnfach.arthouse.model

import android.os.Build
import androidx.annotation.RequiresApi

data class NewsArticle (
    val image: String = "",
    val title: String = "",
    val createdAt: LocalDate? = null,
    val content: String = "",
    var id: String = "",
)
