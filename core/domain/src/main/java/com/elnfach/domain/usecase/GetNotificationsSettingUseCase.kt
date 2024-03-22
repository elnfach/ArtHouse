package com.elnfach.domain.usecase

import com.elnfach.model.NotificationsSetting
import com.elnfach.storage.repository.NotificationsSettingStorage

class GetNotificationsSettingUseCase(
    private val notificationsSettingStorage: NotificationsSettingStorage
) {
    operator fun invoke() : NotificationsSetting
    {
        return notificationsSettingStorage.get()
    }
}