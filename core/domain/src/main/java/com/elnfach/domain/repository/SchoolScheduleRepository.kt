package com.elnfach.domain.repository

import com.elnfach.domain.models.SchoolSchedule

interface SchoolScheduleRepository {
    fun saveSchoolSchedule()
    fun getSchoolSchedule() : List<SchoolSchedule>
}