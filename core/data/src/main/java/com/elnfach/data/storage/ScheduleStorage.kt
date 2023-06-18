package com.elnfach.data.storage

import com.elnfach.data.storage.models.Schedule

interface ScheduleStorage {
    fun get() : List<Schedule>
}