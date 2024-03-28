package com.elnfach.domain.usecase

import com.elnfach.model.NotificationsOption
import com.elnfach.storage.repository.NotificationsSettingStorage

class SaveNotificationsSettingUseCase(
    private val notificationsSettingStorage: NotificationsSettingStorage
) {
    operator fun invoke(notifications: NotificationsOption)
    {
        notificationsSettingStorage.save(notifications)
    }
}