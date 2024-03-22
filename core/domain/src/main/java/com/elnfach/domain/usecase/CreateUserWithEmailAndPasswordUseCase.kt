package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.FirebaseRepository

class CreateUserWithEmailAndPasswordUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke(email: String, password: String) {
        return repository.createUserWithEmailAndPassword(email, password)
    }
}