package com.elnfach.storage

import android.content.Context
import com.elnfach.model.ThemeOption
import com.elnfach.storage.repository.ThemeSettingStorage

const val SHARED_PREFS_THEME_SETTING = "shared_prefs_theme_setting"
const val KEY_THEME_SETTING = "theme_setting"

class ThemeSettingStorageImpl(context: Context) :
    ThemeSettingStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_THEME_SETTING, Context.MODE_PRIVATE)
    override fun save(theme: ThemeOption) {
        sharedPreferences.edit().putString(KEY_THEME_SETTING, theme.value).apply()
    }

    override fun get(): com.elnfach.model.ThemeOption {
        return com.elnfach.model.ThemeOption(
            sharedPreferences.getString(
                KEY_THEME_SETTING,
                "DEFAULT"
            ) ?: "DEFAULT"
        )
    }
}