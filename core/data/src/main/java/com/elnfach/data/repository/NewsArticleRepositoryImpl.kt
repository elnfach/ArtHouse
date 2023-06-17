package com.elnfach.data.repository

import com.elnfach.data.storage.NewsArticlesStorage
import com.elnfach.domain.models.NewsArticle
import com.elnfach.domain.repository.NewsArticleRepository

private const val SHARED_FREFS_NEWS_ARTICLE = "shared_prefs_news_article"
class NewsArticleRepositoryImpl(private val newsArticlesStorage: NewsArticlesStorage) :
    NewsArticleRepository {
    // val sharedPreferences = context.getSharedPreferences(SHARED_FREFS_NEWS_ARTICLE, Context.MODE_PRIVATE)
    override fun saveNewsArticle()
    {
        // sharedPreferences.edit()
    }

    override fun getNewsArticleById(id: Int): NewsArticle {
        val newsArticleStorage = newsArticlesStorage.get()
        for (item in newsArticleStorage)
        {
            if(item.id == id)
            {
                return NewsArticle(item.id, item.image, item.title, item.content)
            }
        }
        return NewsArticle(-1,-1,"Not found", "Not found")
    }

    override fun getNewsArticles() : List<NewsArticle>
    {
        val newsArticleStorage = newsArticlesStorage.get()
        val newsArticleList = mutableListOf<NewsArticle>()
        for (item in newsArticleStorage)
        {
            newsArticleList.add(NewsArticle(item.id, item.image, item.title, item.content))
        }
        return newsArticleList
    }
}