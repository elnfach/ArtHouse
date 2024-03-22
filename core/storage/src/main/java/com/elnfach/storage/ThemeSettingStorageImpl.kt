package com.elnfach.storage

import android.content.Context
import com.elnfach.model.ThemeSetting
import com.elnfach.storage.repository.ThemeSettingStorage

const val SHARED_PREFS_THEME_SETTING = "shared_prefs_theme_setting"
const val KEY_THEME_SETTING = "theme_setting"

class ThemeSettingStorageImpl(context: Context) :
    ThemeSettingStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_THEME_SETTING, Context.MODE_PRIVATE)
    override fun save(theme: ThemeSetting) {
        sharedPreferences.edit().putString(KEY_THEME_SETTING, theme.theme).apply()
    }

    override fun get(): com.elnfach.model.ThemeSetting {
        return com.elnfach.model.ThemeSetting(
            sharedPreferences.getString(
                KEY_THEME_SETTING,
                "DEFAULT"
            ) ?: "DEFAULT"
        )
    }
}