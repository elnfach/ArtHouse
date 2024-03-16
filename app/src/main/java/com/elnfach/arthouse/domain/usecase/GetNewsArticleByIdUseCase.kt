package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

class GetNewsArticleByIdUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(id: String): Flow<Resource<NewsArticle?>> {
        return repository.getNewsArticleById(id)
    }
}