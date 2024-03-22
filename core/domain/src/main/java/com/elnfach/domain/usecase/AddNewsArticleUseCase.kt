package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.NewsRepository
import com.elnfach.model.NewsArticle
import com.elnfach.model.Resource

class AddNewsArticleUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(newsArticle: NewsArticle, callback: (Resource<Boolean>) -> Unit) {
        return repository.addNewsArticles(newsArticle, callback)
    }
}