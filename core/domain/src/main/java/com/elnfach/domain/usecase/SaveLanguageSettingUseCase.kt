package com.elnfach.domain.usecase

import com.elnfach.model.LanguageSetting
import com.elnfach.storage.repository.LanguageSettingStorage

class SaveLanguageSettingUseCase(
    private val languageSettingStorage: LanguageSettingStorage
) {
    operator fun invoke(language: LanguageSetting)
    {
        languageSettingStorage.save(language)
    }
}