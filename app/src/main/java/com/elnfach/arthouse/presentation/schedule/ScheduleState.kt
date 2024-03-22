package com.elnfach.arthouse.presentation.schedule

import com.elnfach.model.Schedule

data class ScheduleState(
    val isLoading: Boolean = false,
    var schedule: List<com.elnfach.model.Schedule> = emptyList(),
    val error: String = ""
)
