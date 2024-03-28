package com.elnfach.domain.repository.firebase

import com.elnfach.model.Resource
import com.elnfach.model.Schedule

interface ScheduleRepository {
    fun addNewLesson(schedule: Schedule, callback: (Resource<Boolean>) -> Unit)
    fun getAllSchedule(callback: (Resource<List<Schedule>>) -> Unit)
}