package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.ScheduleRepository
import com.elnfach.model.Resource
import com.elnfach.model.Schedule

class GetScheduleUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(callback: (Resource<List<Schedule>>) -> Unit) {
        return scheduleRepository.getAllSchedule(callback)
    }
}