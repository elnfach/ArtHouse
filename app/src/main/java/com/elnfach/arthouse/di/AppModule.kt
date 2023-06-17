package com.elnfach.arthouse.di

import com.elnfach.arthouse.presentation.newsline.NewsLineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel<NewsLineViewModel>
    {
        NewsLineViewModel(get(), get())
    }
}