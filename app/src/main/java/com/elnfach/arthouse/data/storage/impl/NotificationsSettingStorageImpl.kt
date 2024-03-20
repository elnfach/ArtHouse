package com.elnfach.arthouse.data.storage.impl

import android.content.Context
import com.elnfach.arthouse.data.storage.NotificationsSettingStorage
import com.elnfach.arthouse.model.NotificationsSetting
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Notifications

const val SHARED_PREFS_NOTIFICATIONS_SETTING = "shared_prefs_notifications_setting"
const val KEY_NOTIFICATIONS_SETTING = "notifications_setting"

class NotificationsSettingStorageImpl(context: Context) : NotificationsSettingStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NOTIFICATIONS_SETTING, Context.MODE_PRIVATE)
    override fun save(notifications: Notifications) {
        sharedPreferences.edit().putString(KEY_NOTIFICATIONS_SETTING, notifications.name).apply()
    }

    override fun get(): NotificationsSetting {
        return NotificationsSetting(sharedPreferences.getString(KEY_NOTIFICATIONS_SETTING, "") ?: Notifications.ALL.name)
    }
}