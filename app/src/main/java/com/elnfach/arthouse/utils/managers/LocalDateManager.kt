package com.elnfach.arthouse.utils.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elnfach.model.LocalDate

object LocalDateManager {
    var localDate by mutableStateOf(LocalDate())
}