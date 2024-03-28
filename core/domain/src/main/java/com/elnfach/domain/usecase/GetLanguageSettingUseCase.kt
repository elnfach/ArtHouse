package com.elnfach.domain.usecase

import com.elnfach.model.LanguageOption
import com.elnfach.storage.repository.LanguageSettingStorage

class GetLanguageSettingUseCase(
    private val languageSettingStorage: LanguageSettingStorage
) {
    operator fun invoke() : LanguageOption
    {
        return languageSettingStorage.get()
    }
}