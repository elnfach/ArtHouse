package com.elnfach.arthouse.utils.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elnfach.arthouse.ui.theme.Theme

object ThemeManager {
    var artHouseTheme by mutableStateOf(Theme.DEFAULT)
}