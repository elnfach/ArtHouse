package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.storage.NotificationsSettingStorage
import com.elnfach.arthouse.model.NotificationsSetting

class GetNotificationsSettingUseCase(
    private val notificationsSettingStorage: NotificationsSettingStorage
) {
    operator fun invoke() : NotificationsSetting
    {
        return notificationsSettingStorage.get()
    }
}