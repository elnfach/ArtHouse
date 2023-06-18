package com.elnfach.domain.usecase

import com.elnfach.domain.models.SchoolSchedule
import com.elnfach.domain.repository.SchoolScheduleRepository

class GetSchoolScheduleUseCase(
    private val scheduleRepository: SchoolScheduleRepository
) {
    fun execute(): List<SchoolSchedule> {
        val schoolScheduleList = scheduleRepository.getSchoolSchedule()
        return quicksort(schoolScheduleList)
    }
    
    private fun quicksort(items: List<SchoolSchedule>): List<SchoolSchedule>{
        if (items.count() < 2){
            return items
        }
        val middle = items[items.count() / 2]

        val equal = items.filter { it == middle }

        val less = items.filter { it.grade < middle.grade }

        val greater = items.filter { it.grade > middle.grade }

        return quicksort(less) + equal + quicksort(greater)
    }
}