package com.elnfach.arthouse.data.repository

import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNewsArticles() : Flow<Resource<List<NewsArticle?>>>
}