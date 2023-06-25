package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.ThemeSettingRepository
import com.elnfach.arthouse.presentation.ui.theme.Theme

class SaveThemeSettingUseCase(
    private val themeSettingRepository: ThemeSettingRepository
) {
    operator fun invoke(theme: Theme)
    {
        themeSettingRepository.save(theme)
    }
}