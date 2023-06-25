package com.elnfach.arthouse.di

import com.elnfach.arthouse.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.arthouse.domain.usecase.GetThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.SaveThemeSettingUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetNewsArticlesUseCase(get())
    }

    factory {
        GetThemeSettingUseCase(get())
    }

    factory {
        SaveThemeSettingUseCase(get())
    }
}