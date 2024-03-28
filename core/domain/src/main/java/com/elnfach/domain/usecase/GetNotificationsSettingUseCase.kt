package com.elnfach.domain.usecase

import com.elnfach.model.NotificationsOption
import com.elnfach.storage.repository.NotificationsSettingStorage

class GetNotificationsSettingUseCase(
    private val notificationsSettingStorage: NotificationsSettingStorage
) {
    operator fun invoke() : NotificationsOption
    {
        return notificationsSettingStorage.get()
    }
}