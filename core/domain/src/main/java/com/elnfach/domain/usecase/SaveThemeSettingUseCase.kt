package com.elnfach.domain.usecase

import com.elnfach.model.ThemeSetting
import com.elnfach.storage.repository.ThemeSettingRepository

class SaveThemeSettingUseCase(
    private val themeSettingRepository: ThemeSettingRepository
) {
    operator fun invoke(theme: ThemeSetting)
    {
        themeSettingRepository.save(theme)
    }
}