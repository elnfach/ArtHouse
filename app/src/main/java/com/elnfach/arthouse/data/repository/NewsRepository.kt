package com.elnfach.arthouse.data.repository

import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource

interface NewsRepository {

    fun addNewsArticles(newsArticle: NewsArticle, callback: (Resource<Boolean>) -> Unit)
    fun getNewsArticles(callback: (Resource<List<NewsArticle>>) -> Unit)
    fun getNewsArticleById(id: String, callback: (Resource<NewsArticle?>) -> Unit)
}