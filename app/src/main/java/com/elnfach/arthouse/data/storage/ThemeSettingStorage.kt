package com.elnfach.arthouse.data.repository.storage

import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme

interface ThemeSettingStorage {
    fun save(theme: Theme)
    fun get() : ThemeSetting
}