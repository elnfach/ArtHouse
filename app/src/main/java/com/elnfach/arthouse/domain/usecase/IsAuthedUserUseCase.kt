package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.FirebaseRepository
import com.elnfach.arthouse.presentation.utils.Resource

class IsAuthedUserUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke(callback: (Resource<Boolean>) -> Unit) {
        return repository.isAuthedUser(callback)
    }
}