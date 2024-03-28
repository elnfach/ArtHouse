package com.elnfach.storage.repository

import com.elnfach.model.ThemeOption

interface ThemeSettingStorage {
    fun save(theme: ThemeOption)
    fun get() : ThemeOption
}