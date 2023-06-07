package com.elnfach.data.repository

import com.elnfach.data.storage.NewsArticleStorage
import com.elnfach.data.storage.models.Article
import com.elnfach.domain.domain.models.NewsArticle
import com.elnfach.domain.domain.repository.NewsArticleRepository

private const val SHARED_FREFS_NEWS_ARTICLE = "shared_prefs_news_article"
class NewsArticleRepositoryImpl(private val newsArticleStorage: NewsArticleStorage) :
    NewsArticleRepository {
    // val sharedPreferences = context.getSharedPreferences(SHARED_FREFS_NEWS_ARTICLE, Context.MODE_PRIVATE)
    override fun saveNewsArticle()
    {
        // sharedPreferences.edit()
    }

    override fun getNewsArticle() : List<NewsArticle>
    {
        val newsArticleStorage = newsArticleStorage.get()
        var newsArticleList = mutableListOf<NewsArticle>()
        for (item in newsArticleStorage)
        {
            newsArticleList.add(NewsArticle(item.id, item.title, item.content))
        }
        return newsArticleList
    }
}