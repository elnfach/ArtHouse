package com.elnfach.arthouse.model

import com.elnfach.arthouse.presentation.ui.theme.Theme

data class ThemeSetting(
    val theme: String = Theme.DEFAULT.name
)
