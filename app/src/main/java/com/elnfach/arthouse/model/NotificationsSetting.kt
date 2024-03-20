package com.elnfach.arthouse.model

import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Notifications

data class NotificationsSetting(
    val notifications: String = Notifications.ALL.name
)
