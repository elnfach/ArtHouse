package com.elnfach.storage

import android.content.Context
import com.elnfach.model.NotificationsSetting
import com.elnfach.storage.repository.NotificationsSettingStorage

const val SHARED_PREFS_NOTIFICATIONS_SETTING = "shared_prefs_notifications_setting"
const val KEY_NOTIFICATIONS_SETTING = "notifications_setting"

class NotificationsSettingStorageImpl(context: Context) :
    NotificationsSettingStorage {
    private val sharedPreferences =
        context.getSharedPreferences(SHARED_PREFS_NOTIFICATIONS_SETTING, Context.MODE_PRIVATE)
    override fun save(notifications: NotificationsSetting) {
        sharedPreferences.edit().putString(KEY_NOTIFICATIONS_SETTING, notifications.value).apply()
    }

    override fun get(): com.elnfach.model.NotificationsSetting {
        return com.elnfach.model.NotificationsSetting(
            sharedPreferences.getString(
                KEY_NOTIFICATIONS_SETTING,
                "ALL"
            ) ?: "ALL"
        )
    }
}