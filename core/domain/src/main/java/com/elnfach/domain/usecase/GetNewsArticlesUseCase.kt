package com.elnfach.domain.usecase

import com.elnfach.firebase.repository.NewsRepository
import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource

class GetNewsArticlesUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(callback: (Resource<List<NewsArticle>>) -> Unit) {
        return repository.getNewsArticles(callback)
    }
}