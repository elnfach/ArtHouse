package com.elnfach.domain.usecase

import com.elnfach.firebase.repository.NewsRepository
import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource

class GetNewsArticleByIdUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(id: String, callback: (Resource<NewsArticle?>) -> Unit) {
        return repository.getNewsArticleById(id, callback)
    }
}