package com.elnfach.data.implementations

import com.elnfach.model.ThemeOption
import com.elnfach.storage.repository.ThemeSettingRepository
import com.elnfach.storage.repository.ThemeSettingStorage

class ThemeSettingRepositoryImpl(
    private val themeSettingStorage: ThemeSettingStorage
) : ThemeSettingRepository {
    override fun save(theme: ThemeOption) {
        themeSettingStorage.save(theme)
    }

    override fun get(): com.elnfach.model.ThemeOption {
        return themeSettingStorage.get()
    }
}