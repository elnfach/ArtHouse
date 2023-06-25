package com.elnfach.arthouse.presentation.schedule

import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.model.Schedule

data class ScheduleState(
    val isLoading: Boolean = false,
    var schedule: List<Schedule> = emptyList(),
    val error: String = ""
)
