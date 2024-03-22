package com.elnfach.firebase.repository

import com.elnfach.model.Resource
import com.elnfach.model.Schedule

interface ScheduleRepository {
    fun saveSchoolSchedule()
    fun getSchoolSchedule(callback: (Resource<List<Schedule>>) -> Unit)
}