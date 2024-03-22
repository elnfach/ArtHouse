package com.elnfach.firebase.repository

import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource

interface NewsRepository {
    fun addNewsArticles(newsArticle: NewsArticle, callback: (Resource<Boolean>) -> Unit)
    fun getNewsArticles(callback: (Resource<List<NewsArticle>>) -> Unit)
    fun getNewsArticleById(id: String, callback: (Resource<NewsArticle?>) -> Unit)
}