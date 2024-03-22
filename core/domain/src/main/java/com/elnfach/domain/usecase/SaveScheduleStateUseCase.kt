package com.elnfach.domain.usecase

import com.elnfach.model.LocalDate
import com.elnfach.storage.repository.ScheduleStateRepository

class SaveScheduleStateUseCase(
    private val scheduleStateRepository: ScheduleStateRepository
) {
    operator fun invoke(date: LocalDate)
    {
        scheduleStateRepository.save(date)
    }
}