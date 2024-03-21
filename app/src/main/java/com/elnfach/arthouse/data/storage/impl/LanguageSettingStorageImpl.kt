package com.elnfach.arthouse.data.storage.impl

import android.content.Context
import com.elnfach.arthouse.data.storage.LanguageSettingStorage
import com.elnfach.arthouse.model.LanguageSetting
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Language

const val SHARED_PREFS_LANGUAGE_SETTING = "shared_prefs_language_setting"
const val KEY_LANGUAGE_SETTING = "language_setting"

class LanguageSettingStorageImpl(context: Context) : LanguageSettingStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_LANGUAGE_SETTING, Context.MODE_PRIVATE)
    override fun save(language: Language) {
        sharedPreferences.edit().putString(KEY_LANGUAGE_SETTING, language.name).apply()
    }

    override fun get(): LanguageSetting {
        return LanguageSetting(sharedPreferences.getString(KEY_LANGUAGE_SETTING, Language.DEFAULT.name) ?: Language.DEFAULT.name)
    }
}