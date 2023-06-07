package com.elnfach.domain.domain.repository

import com.elnfach.domain.domain.models.NewsArticle

interface NewsArticleRepository {
    fun saveNewsArticle()
    fun getNewsArticle() : List<NewsArticle>
}