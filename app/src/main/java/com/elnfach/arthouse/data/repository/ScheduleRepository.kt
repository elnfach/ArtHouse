package com.elnfach.arthouse.data.repository

import com.elnfach.arthouse.model.Schedule
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ScheduleRepository {
    fun saveSchoolSchedule()
    fun getSchoolSchedule() : Flow<Resource<List<Schedule>>>
}