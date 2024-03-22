package com.elnfach.domain.usecase

import com.elnfach.model.LanguageSetting
import com.elnfach.storage.repository.LanguageSettingStorage

class GetLanguageSettingUseCase(
    private val languageSettingStorage: LanguageSettingStorage
) {
    operator fun invoke() : LanguageSetting
    {
        return languageSettingStorage.get()
    }
}