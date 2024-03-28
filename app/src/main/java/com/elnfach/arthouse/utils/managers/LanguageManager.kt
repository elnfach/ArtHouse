package com.elnfach.arthouse.utils.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elnfach.arthouse.profile.details.components.enums.Language

object LanguageManager {
    var language by mutableStateOf(Language.DEFAULT)
}