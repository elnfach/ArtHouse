package com.elnfach.storage.repository

import com.elnfach.model.ThemeOption

interface ThemeSettingRepository {
    fun save(theme: ThemeOption)
    fun get() : ThemeOption
}