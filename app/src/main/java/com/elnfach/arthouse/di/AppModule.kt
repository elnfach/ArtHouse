package com.elnfach.arthouse.di

import com.elnfach.arthouse.presentation.main.MainScreenViewModel
import com.elnfach.arthouse.presentation.profile.ProfileScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainScreenViewModel()
    }

    viewModel {
        ProfileScreenViewModel(get())
    }
}