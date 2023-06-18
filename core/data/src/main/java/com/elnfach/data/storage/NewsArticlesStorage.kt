package com.elnfach.data.storage

import com.elnfach.data.storage.models.Article


interface NewsArticlesStorage {
    fun save()
    fun get() : List<Article>
}