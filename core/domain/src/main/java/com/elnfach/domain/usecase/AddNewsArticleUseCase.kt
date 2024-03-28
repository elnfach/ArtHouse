package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.NewsRepository
import com.elnfach.model.NewsItem
import com.elnfach.model.Resource

class AddNewsArticleUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(newsItem: NewsItem, callback: (Resource<Boolean>) -> Unit) {
        return newsRepository.addNewsItem(newsItem, callback)
    }
}