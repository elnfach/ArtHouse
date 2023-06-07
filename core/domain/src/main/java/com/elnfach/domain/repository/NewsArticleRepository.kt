package com.elnfach.domain.repository

import com.elnfach.domain.models.NewsArticle

interface NewsArticleRepository {
    fun saveNewsArticle()
    fun getNewsArticle() : List<NewsArticle>
}