package com.elnfach.domain.usecase

import com.elnfach.domain.repository.storage.IsAuthedUserStorage

class SaveAuthStatusUseCase(
    private val isAuthedUserStorage: IsAuthedUserStorage
) {
    operator fun invoke(status: String) {
        return isAuthedUserStorage.save(status)
    }
}