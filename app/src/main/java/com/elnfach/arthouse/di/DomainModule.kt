package com.elnfach.arthouse.di

import com.elnfach.domain.usecase.AddNewsArticleUseCase
import com.elnfach.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.elnfach.domain.usecase.GetLanguageSettingUseCase
import com.elnfach.domain.usecase.GetNewsArticleByIdUseCase
import com.elnfach.domain.usecase.GetNewsArticlesUseCase
import com.elnfach.domain.usecase.GetNotificationsSettingUseCase
import com.elnfach.domain.usecase.GetScheduleStateUseCase
import com.elnfach.domain.usecase.GetScheduleUseCase
import com.elnfach.domain.usecase.GetThemeSettingUseCase
import com.elnfach.domain.usecase.IsAuthedUserUseCase
import com.elnfach.domain.usecase.SaveLanguageSettingUseCase
import com.elnfach.domain.usecase.SaveNotificationsSettingUseCase
import com.elnfach.domain.usecase.SaveScheduleStateUseCase
import com.elnfach.domain.usecase.SaveThemeSettingUseCase
import com.elnfach.domain.usecase.SignInUserWithEmailAndPasswordUseCase
import com.elnfach.domain.usecase.SignOutUserUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        com.elnfach.domain.usecase.AddNewsArticleUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.GetNewsArticlesUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.GetThemeSettingUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.SaveThemeSettingUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.GetScheduleUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.GetScheduleStateUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.SaveScheduleStateUseCase(get())
    }
    factory {
        com.elnfach.domain.usecase.GetNewsArticleByIdUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.CreateUserWithEmailAndPasswordUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.IsAuthedUserUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.SignInUserWithEmailAndPasswordUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.SignOutUserUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.GetLanguageSettingUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.GetNotificationsSettingUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.SaveLanguageSettingUseCase(get())
    }

    factory {
        com.elnfach.domain.usecase.SaveNotificationsSettingUseCase(get())
    }
}