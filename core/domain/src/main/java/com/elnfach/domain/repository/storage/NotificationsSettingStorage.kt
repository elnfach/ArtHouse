package com.elnfach.storage.repository

import com.elnfach.model.NotificationsSetting

interface NotificationsSettingStorage {
    fun save(notifications: NotificationsSetting)
    fun get() : NotificationsSetting
}