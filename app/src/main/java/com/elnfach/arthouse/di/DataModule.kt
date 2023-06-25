package com.elnfach.arthouse.di

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.data.repository.ThemeSettingRepository
import com.elnfach.arthouse.data.repository.ThemeSettingRepositoryImpl
import com.elnfach.arthouse.data.repository.storage.ThemeSettingStorage
import com.elnfach.arthouse.data.repository.storage.ThemeSettingStorageImpl
import com.elnfach.arthouse.datastore.repository.NewsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl()
    }

    single<ThemeSettingRepository> {
        ThemeSettingRepositoryImpl(get())
    }
    single<ThemeSettingStorage> {
        ThemeSettingStorageImpl(get())
    }
}