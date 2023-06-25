package com.elnfach.arthouse.model

data class Schedule(
    val id: String = "",
    val date: LocalDate = LocalDate(),
    val start: String = "",
    val end: String = "",
    val lesson: String = "",
    val teacher: String = "",
    val grade: Int = 0,
    val task: ScheduleTask = ScheduleTask(),
    val homeWork: List<String> = emptyList()
)
