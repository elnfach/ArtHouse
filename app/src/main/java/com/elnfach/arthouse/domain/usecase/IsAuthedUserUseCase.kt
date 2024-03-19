package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.FirebaseRepository

class IsAuthedUserUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke() : Boolean {
        return repository.isAuthedUser()
    }
}