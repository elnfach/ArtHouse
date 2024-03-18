package com.elnfach.arthouse.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class ProfileItemType
{
    NONE,
    LARGE_BUTTON,
    TITLE_CATEGORY,
    BUTTON
}

data class ProfileItem(
    val type: ProfileItemType = ProfileItemType.NONE,
    val title: String = "",
    val subTitle: String = "",
    val imageId: Int = 0,
    val contentDescriptionImage: String? = null,
    val onClick: @Composable () -> Unit,
)
