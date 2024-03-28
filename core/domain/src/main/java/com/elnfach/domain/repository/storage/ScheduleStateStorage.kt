package com.elnfach.storage.repository

import com.elnfach.model.LocalDate

interface ScheduleStateStorage {
    fun save(date: LocalDate)
    fun get() : LocalDate
}