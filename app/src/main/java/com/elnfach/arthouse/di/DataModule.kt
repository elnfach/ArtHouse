package com.elnfach.arthouse.di

import com.elnfach.data.repository.NewsArticleRepositoryImpl
import com.elnfach.data.repository.SchoolScheduleRepositoryImpl
import com.elnfach.data.storage.NewsArticlesStorage
import com.elnfach.data.storage.NewsArticlesStorageImpl
import com.elnfach.data.storage.ScheduleStorage
import com.elnfach.data.storage.ScheduleStorageImpl
import com.elnfach.domain.repository.NewsArticleRepository
import com.elnfach.domain.repository.SchoolScheduleRepository
import org.koin.dsl.module

val dataModule = module {

    single<NewsArticlesStorage>
    {
        NewsArticlesStorageImpl()
    }
    single<NewsArticleRepository>
    {
        NewsArticleRepositoryImpl(get())
    }

    single<SchoolScheduleRepository>
    {
        SchoolScheduleRepositoryImpl(get())
    }
    single<ScheduleStorage>
    {
        ScheduleStorageImpl()
    }
}