package com.elnfach.arthouse.data.repository.storage

import android.content.Context
import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme

const val SHARED_PREFS_THEME_SETTING = "shared_prefs_theme_setting"
const val KEY_THEME_SETTING = "theme_setting"

class ThemeSettingStorageImpl(context: Context) : ThemeSettingStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_THEME_SETTING, Context.MODE_PRIVATE)
    override fun save(theme: Theme) {
        sharedPreferences.edit().putString(KEY_THEME_SETTING, theme.name).apply()
    }

    override fun get(): ThemeSetting {
        return ThemeSetting(sharedPreferences.getString(KEY_THEME_SETTING, "") ?: Theme.DEFAULT.name)
    }
}