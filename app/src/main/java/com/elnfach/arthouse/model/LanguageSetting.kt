package com.elnfach.arthouse.model

import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Language

data class LanguageSetting(
    val language: String = Language.DEFAULT.name
)
