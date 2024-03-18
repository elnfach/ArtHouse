package com.elnfach.arthouse.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
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
    // MODIFIER OPTIONS
    val boxModifier: Modifier = Modifier,
    val columnModifier: Modifier = Modifier,
    val rowModifier: Modifier = Modifier,
    val innerColumnModifier: Modifier = Modifier,
    val spacerModifier: Modifier = Modifier,
    val textModifier: Modifier = Modifier,

    // OPTIONS
    val type: ProfileItemType = ProfileItemType.NONE,
    val boxHorizontalPadding: Dp = 24.dp,
    val columnPadding: Dp = 4.dp,
    val innerColumnPadding: Dp = 12.dp,
    val rowPadding: Dp = 12.dp,

    // ROW DETAILS
    val rowHorizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    val rowVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,

    // COLUMN DETAILS
    val columnVerticalArrangement: Arrangement.Vertical = Arrangement.Top,
    val columnHorizontalAlignment: Alignment.Horizontal = Alignment.Start,

    // DATA
    val title: String = "",
    val subTitle: String = "",
    val imageId: Int = 0,
    val contentDescriptionImage: String? = null
)
