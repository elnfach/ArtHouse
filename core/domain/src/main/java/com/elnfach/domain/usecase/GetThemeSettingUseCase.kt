package com.elnfach.domain.usecase

import com.elnfach.model.ThemeOption
import com.elnfach.storage.repository.ThemeSettingRepository

class GetThemeSettingUseCase(
    private val themeSettingRepository: ThemeSettingRepository
) {
    operator fun invoke() : ThemeOption
    {
        return themeSettingRepository.get()
    }
}