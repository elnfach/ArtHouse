package com.elnfach.arthouse.data.repository.impl

import com.elnfach.arthouse.data.repository.ScheduleStateRepository
import com.elnfach.arthouse.data.storage.ScheduleStateStorage
import com.elnfach.arthouse.model.LocalDate
import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme

class ScheduleStateRepositoryImpl(
    private val scheduleStateStorage: ScheduleStateStorage
) : ScheduleStateRepository {
    override fun save(date: LocalDate)
    {
        scheduleStateStorage.save(date)
    }
    override fun get() : LocalDate
    {
        return scheduleStateStorage.get()
    }
}