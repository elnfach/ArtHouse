package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.FirebaseRepository

class SignInUserWithEmailAndPasswordUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke(email: String, password: String) {
        return repository.signInUserWithEmailAndPassword(email, password)
    }
}