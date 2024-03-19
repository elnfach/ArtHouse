package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.FirebaseRepository

class SignOutUserUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke() {
        return repository.signOutUser()
    }
}