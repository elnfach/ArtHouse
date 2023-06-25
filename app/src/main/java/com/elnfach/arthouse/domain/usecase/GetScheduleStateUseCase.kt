package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.ScheduleStateRepository
import com.elnfach.arthouse.model.LocalDate

class GetScheduleStateUseCase(
    private val scheduleStateRepository: ScheduleStateRepository
) {
    operator fun invoke() : LocalDate
    {
        return scheduleStateRepository.get()
    }
}