package com.elnfach.arthouse.di

import com.elnfach.data.implementations.ScheduleStateRepositoryImpl
import com.elnfach.data.implementations.ThemeSettingRepositoryImpl
import com.elnfach.firebase.FirebaseRepositoryImpl
import com.elnfach.firebase.NewsRepositoryImpl
import com.elnfach.firebase.ScheduleRepositoryImpl
import com.elnfach.firebase.repository.NewsRepository
import com.elnfach.storage.LanguageSettingStorageImpl
import com.elnfach.storage.NotificationsSettingStorageImpl
import com.elnfach.storage.ScheduleStateStorageImpl
import com.elnfach.storage.ThemeSettingStorageImpl
import com.elnfach.storage.repository.LanguageSettingStorage
import com.elnfach.storage.repository.NotificationsSettingStorage
import com.elnfach.storage.repository.ScheduleStateRepository
import com.elnfach.storage.repository.ScheduleStateStorage
import com.elnfach.storage.repository.ThemeSettingRepository
import com.elnfach.storage.repository.ThemeSettingStorage
import org.koin.dsl.module

val dataModule = module {

    single<NewsRepository> {
        NewsRepositoryImpl()
    }

    single<com.elnfach.firebase.repository.FirebaseRepository> {
        FirebaseRepositoryImpl()
    }

    single<ThemeSettingRepository> {
        ThemeSettingRepositoryImpl(get())
    }

    single<ThemeSettingStorage> {
        ThemeSettingStorageImpl(get())
    }

    single<com.elnfach.firebase.repository.ScheduleRepository> {
        ScheduleRepositoryImpl()
    }

    single<ScheduleStateRepository> {
        ScheduleStateRepositoryImpl(get())
    }

    single<ScheduleStateStorage> {
        ScheduleStateStorageImpl(get())
    }

    single<LanguageSettingStorage> {
        LanguageSettingStorageImpl(get())
    }

    single<NotificationsSettingStorage> {
        NotificationsSettingStorageImpl(get())
    }
}