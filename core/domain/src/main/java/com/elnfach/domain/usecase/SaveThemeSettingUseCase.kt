package com.elnfach.domain.usecase

import com.elnfach.model.ThemeOption
import com.elnfach.storage.repository.ThemeSettingRepository

class SaveThemeSettingUseCase(
    private val themeSettingRepository: ThemeSettingRepository
) {
    operator fun invoke(theme: ThemeOption)
    {
        themeSettingRepository.save(theme)
    }
}