package com.elnfach.arthouse.data.storage

import com.elnfach.arthouse.model.NotificationsSetting
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Notifications

interface NotificationsSettingStorage {

    fun save(notifications: Notifications)
    fun get() : NotificationsSetting
}