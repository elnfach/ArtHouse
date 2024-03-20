package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.storage.NotificationsSettingStorage
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Notifications

class SaveNotificationsSettingUseCase(
    private val notificationsSettingStorage: NotificationsSettingStorage
) {
    operator fun invoke(notifications: Notifications)
    {
        notificationsSettingStorage.save(notifications)
    }
}