package com.elnfach.domain.usecase

import com.elnfach.model.LanguageOption
import com.elnfach.storage.repository.LanguageSettingStorage

class SaveLanguageSettingUseCase(
    private val languageSettingStorage: LanguageSettingStorage
) {
    operator fun invoke(language: LanguageOption)
    {
        languageSettingStorage.save(language)
    }
}