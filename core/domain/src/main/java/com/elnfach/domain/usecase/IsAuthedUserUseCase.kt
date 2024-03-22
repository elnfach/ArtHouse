package com.elnfach.domain.usecase

import com.elnfach.firebase.repository.FirebaseRepository
import com.elnfach.model.Resource

class IsAuthedUserUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke(callback: (Resource<Boolean>) -> Unit) {
        return repository.isAuthedUser(callback)
    }
}