package com.elnfach.arthouse.data.repository.impl

import com.elnfach.arthouse.data.repository.ThemeSettingRepository
import com.elnfach.arthouse.data.repository.storage.ThemeSettingStorage
import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme

class ThemeSettingRepositoryImpl(
    private val themeSettingStorage: ThemeSettingStorage
) : ThemeSettingRepository {
    override fun save(theme: Theme) {
        themeSettingStorage.save(theme)
    }

    override fun get(): ThemeSetting {
        return themeSettingStorage.get()
    }
}