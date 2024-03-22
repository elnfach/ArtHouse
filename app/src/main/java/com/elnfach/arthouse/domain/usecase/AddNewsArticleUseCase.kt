package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource

class AddNewsArticleUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(newsArticle: NewsArticle, callback: (Resource<Boolean>) -> Unit) {
        return repository.addNewsArticles(newsArticle, callback)
    }
}