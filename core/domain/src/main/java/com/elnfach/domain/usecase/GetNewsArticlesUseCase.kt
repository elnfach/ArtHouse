package com.elnfach.domain.domain.usecase

import com.elnfach.domain.domain.models.NewsArticle
import com.elnfach.domain.domain.repository.NewsArticleRepository

class GetNewsArticlesUseCase(private val newsArticlesRepository : NewsArticleRepository) {
    fun execute(): List<NewsArticle>
    {
        return newsArticlesRepository.getNewsArticle()
    }
}