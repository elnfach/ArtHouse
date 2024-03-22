package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.FirebaseRepository

class SignOutUserUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke() {
        return repository.signOutUser()
    }
}