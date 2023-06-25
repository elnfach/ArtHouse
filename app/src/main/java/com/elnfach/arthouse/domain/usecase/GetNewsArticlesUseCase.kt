package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class GetNewsArticlesUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<NewsArticle?>>> {
        return repository.getNewsArticles()
    }
}