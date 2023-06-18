package com.elnfach.domain.models

data class SchoolSchedule(
    val id: Int,
    val date: LocaleDate,
    val start: String,
    val end: String,
    val lesson: String,
    val teacher: String,
    val grade: Int,
    val task: ScheduleTask,
    val homeWork: List<String>
)