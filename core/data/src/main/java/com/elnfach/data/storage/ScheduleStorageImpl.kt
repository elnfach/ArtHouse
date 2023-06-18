package com.elnfach.data.storage

import com.elnfach.data.storage.models.Date
import com.elnfach.data.storage.models.Schedule
import com.elnfach.data.storage.models.Task

class ScheduleStorageImpl : ScheduleStorage {
    override fun get(): List<Schedule> {
        return listOf(
        )
    }
}