package com.elnfach.arthouse.di

import com.elnfach.arthouse.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel<MainViewModel>
    {
        MainViewModel(get())
    }
}