package com.elnfach.arthouse.di

import com.elnfach.arthouse.presentation.create.newsArticle.CreateNewsArticleScreenViewModel
import com.elnfach.arthouse.presentation.foryou.ForYouScreenViewModel
import com.elnfach.arthouse.presentation.main.MainScreenViewModel
import com.elnfach.arthouse.presentation.profile.ProfileScreenViewModel
import com.elnfach.arthouse.presentation.schedule.ScheduleScreenViewModel
import com.elnfach.arthouse.presentation.settings.SettingsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainScreenViewModel()
    }

    viewModel {
        ProfileScreenViewModel(get())
    }

    viewModel {
        ForYouScreenViewModel(get(), get())
    }

    viewModel {
        SettingsScreenViewModel(get(), get())
    }

    viewModel {
        ScheduleScreenViewModel(get(), get(), get())
    }

    viewModel {
        CreateNewsArticleScreenViewModel(get())
    }

   /* viewModel {
        ThemeViewModel(get(), get())
    }*/
}