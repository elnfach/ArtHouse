package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.ScheduleRepository
import com.elnfach.model.Resource
import com.elnfach.model.Schedule

class AddNewLessonUseCase(
    private val scheduleRepository: ScheduleRepository
) {
    operator fun invoke(lesson: Schedule, callback: (Resource<Boolean>) -> Unit)
    {
        return scheduleRepository.addNewLesson(lesson, callback)
    }
}