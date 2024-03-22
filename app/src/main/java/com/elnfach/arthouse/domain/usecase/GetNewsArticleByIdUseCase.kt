package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource

class GetNewsArticleByIdUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(id: String, callback: (Resource<NewsArticle?>) -> Unit) {
        return repository.getNewsArticleById(id, callback)
    }
}