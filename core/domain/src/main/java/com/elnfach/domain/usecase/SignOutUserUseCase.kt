package com.elnfach.domain.usecase

import com.elnfach.firebase.repository.FirebaseRepository

class SignOutUserUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke() {
        return repository.signOutUser()
    }
}