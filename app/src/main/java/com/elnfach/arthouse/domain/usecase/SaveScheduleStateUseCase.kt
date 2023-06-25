package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.ScheduleStateRepository
import com.elnfach.arthouse.model.LocalDate

class SaveScheduleStateUseCase(
    private val scheduleStateRepository: ScheduleStateRepository
) {
    operator fun invoke(date: LocalDate)
    {
        scheduleStateRepository.save(date)
    }
}