package com.elnfach.storage.repository

import com.elnfach.model.ThemeSetting

interface ThemeSettingRepository {
    fun save(theme: ThemeSetting)
    fun get() : ThemeSetting
}