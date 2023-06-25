package com.elnfach.arthouse.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elnfach.arthouse.presentation.ui.theme.Theme

object LocalDateManager {
    var localDate by mutableStateOf(LocalDate())
}

data class LocalDate(
    val year: Int = 2023,
    val monthNumber: Int = 6,
    val dayOfMonth: Int = 1,
    val hour: Int = 12,
    val minute: Int = 23
)
