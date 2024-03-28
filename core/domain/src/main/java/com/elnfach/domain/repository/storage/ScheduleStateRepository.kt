package com.elnfach.domain.repository.storage

import com.elnfach.model.LocalDate

interface ScheduleStateRepository {
    fun save(date: LocalDate)
    fun get() : LocalDate
}