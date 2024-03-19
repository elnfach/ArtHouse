package com.elnfach.arthouse.presentation.profile.components.dataclass

import androidx.compose.runtime.Composable
import com.elnfach.arthouse.presentation.profile.components.enums.ProfileItemType

data class ProfileItem(
    val type: ProfileItemType = ProfileItemType.NONE,
    val title: String = "",
    val subTitle: String = "",
    val imageId: Int = 0,
    val contentDescriptionImage: String? = null,
    val onClick: @Composable () -> Unit,
)
