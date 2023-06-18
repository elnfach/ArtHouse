package com.elnfach.data.repository

import com.elnfach.data.storage.ScheduleStorage
import com.elnfach.domain.models.LocaleDate
import com.elnfach.domain.models.ScheduleTask
import com.elnfach.domain.models.SchoolSchedule
import com.elnfach.domain.repository.SchoolScheduleRepository

class SchoolScheduleRepositoryImpl(
    private val scheduleStorage: ScheduleStorage
) : SchoolScheduleRepository {
    override fun saveSchoolSchedule() {
        TODO("Not yet implemented")
    }

    override fun getSchoolSchedule(): List<SchoolSchedule> {
        val scheduleStorage = scheduleStorage.get()
        val scheduleList = mutableListOf<SchoolSchedule>()
        for (item in scheduleStorage)
        {
            scheduleList.add(
                SchoolSchedule(
                    item.id,
                    LocaleDate(item.date.year, item.date.month, item.date.dayOfMonth),
                    item.start,
                    item.end,
                    item.lesson,
                    item.teacher,
                    item.grade,
                    ScheduleTask(item.task.id, item.task.accessCode, item.task.nameApp, item.task.link),
                    item.homeWork
                ))
        }
        return scheduleList
    }
}