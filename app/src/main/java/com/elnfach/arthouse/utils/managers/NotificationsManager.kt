package com.elnfach.arthouse.utils.managers

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.elnfach.arthouse.profile.details.components.enums.Notifications

object NotificationsManager {
    var notifications by mutableStateOf(Notifications.ALL)
}