package com.elnfach.domain.usecase

import com.elnfach.firebase.repository.FirebaseRepository

class SignInUserWithEmailAndPasswordUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke(email: String, password: String) {
        return repository.signInUserWithEmailAndPassword(email, password)
    }
}