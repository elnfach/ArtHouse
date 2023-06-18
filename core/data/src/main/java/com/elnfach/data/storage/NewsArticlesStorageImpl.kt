package com.elnfach.data.storage

import com.elnfach.data.R
import com.elnfach.data.storage.models.Article

class NewsArticlesStorageImpl() : NewsArticlesStorage
{
    override fun save() {
    }

    override fun get(): List<Article> {
        return listOf(
        )
    }
}