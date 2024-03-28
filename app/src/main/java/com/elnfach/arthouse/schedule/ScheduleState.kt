package com.elnfach.arthouse.schedule

import com.elnfach.model.Schedule

data class ScheduleState(
    var value: List<Schedule> = emptyList(),
    val error: String = "",
    val loading: Boolean = false
)
