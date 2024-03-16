package com.elnfach.arthouse.data.repository

import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun addNewsArticles(newsArticle: NewsArticle) : Flow<Resource<Boolean>>
    fun getNewsArticles() : Flow<Resource<List<NewsArticle?>>>
    fun getNewsArticleById(id: String) : Flow<Resource<NewsArticle?>>
}