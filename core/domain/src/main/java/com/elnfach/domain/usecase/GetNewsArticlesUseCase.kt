package com.elnfach.domain.usecase

import com.elnfach.domain.models.NewsArticle
import com.elnfach.domain.repository.NewsArticleRepository

class GetNewsArticlesUseCase(
    private val newsArticlesRepository : NewsArticleRepository
) {
    fun execute(): List<NewsArticle>
    {
        return newsArticlesRepository.getNewsArticles()
    }
}