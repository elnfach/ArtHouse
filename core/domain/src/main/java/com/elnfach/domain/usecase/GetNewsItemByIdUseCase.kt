package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.NewsRepository
import com.elnfach.model.NewsItem
import com.elnfach.model.Resource

class GetNewsItemByIdUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(id: String, callback: (Resource<NewsItem>) -> Unit) {
        return newsRepository.getNewsItemById(id, callback)
    }
}