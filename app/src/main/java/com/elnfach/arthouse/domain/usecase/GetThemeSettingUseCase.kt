package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.ThemeSettingRepository
import com.elnfach.arthouse.model.ThemeSetting

class GetThemeSettingUseCase(
    private val themeSettingRepository: ThemeSettingRepository
) {
    operator fun invoke() : ThemeSetting
    {
        return themeSettingRepository.get()
    }
}