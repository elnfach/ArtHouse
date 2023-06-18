package com.elnfach.domain.usecase

import com.elnfach.domain.models.SchoolSchedule
import com.elnfach.domain.repository.SchoolScheduleRepository

class GetSchoolScheduleUseCase(
    private val scheduleRepository: SchoolScheduleRepository
) {
    fun execute() : List<SchoolSchedule>
    {
        return scheduleRepository.getSchoolSchedule()
    }
}