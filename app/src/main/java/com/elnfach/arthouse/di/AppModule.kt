package com.elnfach.arthouse.di

import com.elnfach.arthouse.presentation.MainViewModel
import com.elnfach.arthouse.presentation.newsline.NewsLineViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel<MainViewModel>
    {
        MainViewModel(get())
    }
    viewModel<NewsLineViewModel>
    {
        NewsLineViewModel(get(), get())
    }
}