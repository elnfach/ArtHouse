package com.elnfach.arthouse.data.repository

import com.elnfach.arthouse.model.LocalDate

interface ScheduleStateRepository {
    fun save(date: LocalDate)
    fun get() : LocalDate
}