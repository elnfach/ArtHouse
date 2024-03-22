package com.elnfach.domain.usecase

import com.elnfach.model.ThemeSetting
import com.elnfach.storage.repository.ThemeSettingRepository

class GetThemeSettingUseCase(
    private val themeSettingRepository: ThemeSettingRepository
) {
    operator fun invoke() : ThemeSetting
    {
        return themeSettingRepository.get()
    }
}