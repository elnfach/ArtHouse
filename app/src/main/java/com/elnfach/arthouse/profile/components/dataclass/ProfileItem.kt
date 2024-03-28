package com.elnfach.arthouse.profile.components.dataclass

import androidx.compose.ui.graphics.painter.Painter
import com.elnfach.arthouse.profile.components.enums.ProfileItemType

data class ProfileItem(
    val type: ProfileItemType = ProfileItemType.NONE,
    val title: String = "",
    val subTitle: String = "",
    val image: Painter? = null,
    val contentDescriptionImage: String? = null,
    val onClick: () -> Unit,
)
