package com.elnfach.domain.usecase

import com.elnfach.domain.repository.storage.IsAuthedUserStorage

class GetIsAuthedUserUseCase(
    private val isAuthedUserStorage: IsAuthedUserStorage
) {
    operator fun invoke() : String {
        return isAuthedUserStorage.get()
    }
}