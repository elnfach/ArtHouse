package com.elnfach.arthouse.di

import com.elnfach.domain.usecase.GetNewsArticleByIdUseCase
import com.elnfach.domain.usecase.GetNewsArticlesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory<GetNewsArticlesUseCase>
    {
        GetNewsArticlesUseCase(get())
    }
    factory<GetNewsArticleByIdUseCase> {
        GetNewsArticleByIdUseCase(get())
    }
}