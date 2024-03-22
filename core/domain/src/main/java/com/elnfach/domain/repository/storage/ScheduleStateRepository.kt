package com.elnfach.storage.repository

import com.elnfach.model.LocalDate

interface ScheduleStateRepository {
    fun save(date: LocalDate)
    fun get() : LocalDate
}