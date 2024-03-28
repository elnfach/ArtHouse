package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.NewsRepository
import com.elnfach.model.NewsItem
import com.elnfach.model.Resource

class GetNewsByOrderUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(order: String, callback: (Resource<List<NewsItem>>) -> Unit) {
        return newsRepository.getNewsArticlesByOrder(order, callback)
    }
}