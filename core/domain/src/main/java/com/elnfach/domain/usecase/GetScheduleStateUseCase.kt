package com.elnfach.domain.usecase

import com.elnfach.model.LocalDate
import com.elnfach.domain.repository.storage.ScheduleStateRepository

class GetScheduleStateUseCase(
    private val scheduleStateRepository: ScheduleStateRepository
) {
    operator fun invoke() : LocalDate
    {
        return scheduleStateRepository.get()
    }
}