package com.elnfach.arthouse.data.repository

import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme

interface ThemeSettingRepository {
    fun save(theme: Theme)
    fun get() : ThemeSetting
}