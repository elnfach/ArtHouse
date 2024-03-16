package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

class AddNewsArticleUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(newsArticle: NewsArticle): Flow<Resource<Boolean>> {
        return repository.addNewsArticles(newsArticle)
    }
}