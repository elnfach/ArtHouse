package com.elnfach.arthouse.di

import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.data.repository.ScheduleRepository
import com.elnfach.arthouse.data.repository.ScheduleStateRepository
import com.elnfach.arthouse.data.repository.ThemeSettingRepository
import com.elnfach.arthouse.data.repository.impl.ScheduleStateRepositoryImpl
import com.elnfach.arthouse.data.repository.impl.ThemeSettingRepositoryImpl
import com.elnfach.arthouse.data.repository.storage.ThemeSettingStorage
import com.elnfach.arthouse.data.repository.storage.ThemeSettingStorageImpl
import com.elnfach.arthouse.data.storage.ScheduleStateStorage
import com.elnfach.arthouse.data.storage.ScheduleStateStorageImpl
import com.elnfach.arthouse.datastore.repository.NewsRepositoryImpl
import com.elnfach.arthouse.datastore.repository.ScheduleRepositoryImpl
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

    single<ScheduleRepository>
    {
        ScheduleRepositoryImpl()
    }

    single<ScheduleStateRepository> {
        ScheduleStateRepositoryImpl(get())
    }
    single<ScheduleStateStorage> {
        ScheduleStateStorageImpl(get())
    }
}