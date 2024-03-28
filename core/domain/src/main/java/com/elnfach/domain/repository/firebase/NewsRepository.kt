package com.elnfach.domain.repository.firebase

import com.elnfach.model.NewsItem
import com.elnfach.model.Resource

interface NewsRepository {
    fun addNewsItem(newsItem: NewsItem, callback: (Resource<Boolean>) -> Unit)
    fun getNewsArticlesByOrder(order: String, callback: (Resource<List<NewsItem>>) -> Unit)
    fun getNewsItemById(id: String, callback: (Resource<NewsItem>) -> Unit)
    fun deleteNewsItemById(id: String, callback: (Resource<Boolean>) -> Unit)
}