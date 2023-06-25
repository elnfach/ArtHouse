package com.elnfach.arthouse.data.storage

import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme

interface ScheduleStateStorage {
    fun save(date: LocalDate)
    fun get() : LocalDate
}