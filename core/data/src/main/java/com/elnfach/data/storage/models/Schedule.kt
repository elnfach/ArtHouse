package com.elnfach.data.storage.models

data class Schedule (
    val id: Int,
    val date: Date,
    val start: String,
    val end: String,
    val lesson: String,
    val teacher: String,
    val grade: Int,
    val task: Task,
    val homeWork: List<String>
)