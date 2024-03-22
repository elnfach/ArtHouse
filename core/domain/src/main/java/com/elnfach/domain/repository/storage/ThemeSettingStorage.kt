package com.elnfach.storage.repository

import com.elnfach.model.ThemeSetting

interface ThemeSettingStorage {
    fun save(theme: ThemeSetting)
    fun get() : ThemeSetting
}