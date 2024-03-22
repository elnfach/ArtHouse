package com.elnfach.arthouse.di

import com.elnfach.arthouse.data.repository.FirebaseRepository
import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.data.repository.ScheduleRepository
import com.elnfach.arthouse.data.repository.ScheduleStateRepository
import com.elnfach.arthouse.data.repository.ThemeSettingRepository
import com.elnfach.arthouse.data.repository.impl.FirebaseRepositoryImpl
import com.elnfach.arthouse.data.repository.impl.ScheduleStateRepositoryImpl
import com.elnfach.arthouse.data.repository.impl.ThemeSettingRepositoryImpl
import com.elnfach.arthouse.data.repository.storage.ThemeSettingStorage
import com.elnfach.arthouse.data.repository.storage.ThemeSettingStorageImpl
import com.elnfach.arthouse.data.storage.LanguageSettingStorage
import com.elnfach.arthouse.data.storage.NotificationsSettingStorage
import com.elnfach.arthouse.data.storage.ScheduleStateStorage
import com.elnfach.arthouse.data.storage.impl.LanguageSettingStorageImpl
import com.elnfach.arthouse.data.storage.impl.NotificationsSettingStorageImpl
import com.elnfach.arthouse.data.storage.impl.ScheduleStateStorageImpl
import com.elnfach.arthouse.data.repository.impl.NewsRepositoryImpl
import com.elnfach.arthouse.data.repository.impl.ScheduleRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single<NewsRepository> {
        NewsRepositoryImpl()
    }

    single<FirebaseRepository> {
        FirebaseRepositoryImpl(get())
    }

    single<ThemeSettingRepository> {
        ThemeSettingRepositoryImpl(get())
    }

    single<ThemeSettingStorage> {
        ThemeSettingStorageImpl(get())
    }

    single<ScheduleRepository> {
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