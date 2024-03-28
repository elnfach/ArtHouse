package com.elnfach.storage.repository

import com.elnfach.model.LanguageOption

interface LanguageSettingStorage {
    fun save(language: LanguageOption)
    fun get() : LanguageOption
}