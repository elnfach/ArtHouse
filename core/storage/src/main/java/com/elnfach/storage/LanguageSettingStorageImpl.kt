package com.elnfach.storage

import android.content.Context
import com.elnfach.model.LanguageSetting
import com.elnfach.storage.repository.LanguageSettingStorage

const val SHARED_PREFS_LANGUAGE_SETTING = "shared_prefs_language_setting"
const val KEY_LANGUAGE_SETTING = "language_setting"

class LanguageSettingStorageImpl(context: Context) : LanguageSettingStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE_SETTING, Context.MODE_PRIVATE)
    override fun save(language: LanguageSetting) {
        sharedPreferences.edit().putString(KEY_LANGUAGE_SETTING, language.language).apply()
    }

    override fun get(): LanguageSetting {
        return LanguageSetting(
            sharedPreferences.getString(
                KEY_LANGUAGE_SETTING,
                "DEFAULT"
            ) ?: "DEFAULT"
        )
    }
}