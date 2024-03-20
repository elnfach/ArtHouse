package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.storage.LanguageSettingStorage
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Language

class SaveLanguageSettingUseCase(
    private val languageSettingStorage: LanguageSettingStorage
) {
    operator fun invoke(language: Language)
    {
        languageSettingStorage.save(language)
    }
}