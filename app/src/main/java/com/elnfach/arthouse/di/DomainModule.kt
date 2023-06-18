package com.elnfach.arthouse.di

import com.elnfach.domain.usecase.GetNewsArticleByIdUseCase
import com.elnfach.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.domain.usecase.GetSchoolScheduleUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetNewsArticlesUseCase>
    {
        GetNewsArticlesUseCase(get())
    }
    factory<GetNewsArticleByIdUseCase> {
        GetNewsArticleByIdUseCase(get())
    }
    factory<GetSchoolScheduleUseCase> {
        GetSchoolScheduleUseCase(get())
    }
}