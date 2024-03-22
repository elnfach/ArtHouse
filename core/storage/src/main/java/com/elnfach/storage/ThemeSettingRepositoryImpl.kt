package com.elnfach.data.implementations

import com.elnfach.model.ThemeSetting
import com.elnfach.storage.repository.ThemeSettingRepository
import com.elnfach.storage.repository.ThemeSettingStorage

class ThemeSettingRepositoryImpl(
    private val themeSettingStorage: ThemeSettingStorage
) : ThemeSettingRepository {
    override fun save(theme: ThemeSetting) {
        themeSettingStorage.save(theme)
    }

    override fun get(): com.elnfach.model.ThemeSetting {
        return themeSettingStorage.get()
    }
}