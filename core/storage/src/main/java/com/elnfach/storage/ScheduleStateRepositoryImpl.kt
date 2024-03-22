package com.elnfach.data.implementations

import com.elnfach.model.LocalDate
import com.elnfach.storage.repository.ScheduleStateRepository
import com.elnfach.storage.repository.ScheduleStateStorage

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