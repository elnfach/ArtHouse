package com.elnfach.storage.repository

import com.elnfach.model.LanguageSetting

interface LanguageSettingStorage {
    fun save(language: LanguageSetting)
    fun get() : LanguageSetting
}