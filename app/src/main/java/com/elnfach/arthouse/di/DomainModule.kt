package com.elnfach.arthouse.di

import com.elnfach.arthouse.domain.usecase.AddNewsArticleUseCase
import com.elnfach.arthouse.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.elnfach.arthouse.domain.usecase.GetNewsArticleByIdUseCase
import com.elnfach.arthouse.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.arthouse.domain.usecase.GetScheduleStateUseCase
import com.elnfach.arthouse.domain.usecase.GetScheduleUseCase
import com.elnfach.arthouse.domain.usecase.GetThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.IsAuthedUserUseCase
import com.elnfach.arthouse.domain.usecase.SaveScheduleStateUseCase
import com.elnfach.arthouse.domain.usecase.SaveThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.SignInUserWithEmailAndPasswordUseCase
import com.elnfach.arthouse.domain.usecase.SignOutUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        AddNewsArticleUseCase(get())
    }

    factory {
        GetNewsArticlesUseCase(get())
    }

    factory {
        GetThemeSettingUseCase(get())
    }

    factory {
        SaveThemeSettingUseCase(get())
    }

    factory {
        GetScheduleUseCase(get())
    }

    factory {
        GetScheduleStateUseCase(get())
    }

    factory {
        SaveScheduleStateUseCase(get())
    }
    factory {
        GetNewsArticleByIdUseCase(get())
    }

    factory {
        CreateUserWithEmailAndPasswordUseCase(get())
    }

    factory {
        IsAuthedUserUseCase(get())
    }

    factory {
        SignInUserWithEmailAndPasswordUseCase(get())
    }

    factory {
        SignOutUserUseCase(get())
    }
}