package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.storage.LanguageSettingStorage
import com.elnfach.arthouse.model.LanguageSetting

class GetLanguageSettingUseCase(
    private val languageSettingStorage: LanguageSettingStorage
) {
    operator fun invoke() : LanguageSetting
    {
        return languageSettingStorage.get()
    }
}