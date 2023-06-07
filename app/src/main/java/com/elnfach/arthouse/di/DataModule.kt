package com.elnfach.arthouse.di

import com.elnfach.data.repository.NewsArticleRepositoryImpl
import com.elnfach.data.storage.NewsArticlesStorage
import com.elnfach.domain.repository.NewsArticleRepository
import org.koin.dsl.module

val dataModule = module {

    single<NewsArticlesStorage>
    {
        NewsArticlesStorage()
    }
    single<NewsArticleRepository>
    {
        NewsArticleRepositoryImpl(get())
    }
}