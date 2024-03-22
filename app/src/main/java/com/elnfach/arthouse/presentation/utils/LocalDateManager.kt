package com.elnfach.arthouse.presentation.utils

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elnfach.model.LocalDate

object LocalDateManager {
    var localDate by mutableStateOf(com.elnfach.model.LocalDate())
}