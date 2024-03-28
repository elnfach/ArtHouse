package com.elnfach.storage.repository

import com.elnfach.model.NotificationsOption

interface NotificationsSettingStorage {
    fun save(notifications: NotificationsOption)
    fun get() : NotificationsOption
}