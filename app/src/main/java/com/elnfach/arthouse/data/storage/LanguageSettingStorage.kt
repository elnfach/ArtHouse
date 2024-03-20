package com.elnfach.arthouse.data.storage

import com.elnfach.arthouse.model.LanguageSetting
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Language

interface LanguageSettingStorage {
    fun save(language: Language)
    fun get() : LanguageSetting
}