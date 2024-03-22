package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource

class GetNewsArticlesUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(callback: (Resource<List<NewsArticle>>) -> Unit) {
        return repository.getNewsArticles(callback)
    }
}