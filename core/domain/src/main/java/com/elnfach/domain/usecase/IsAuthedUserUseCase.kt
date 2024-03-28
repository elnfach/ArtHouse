package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.UserRepository
import com.elnfach.model.Resource

class IsAuthedUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(callback: (Resource<Boolean>) -> Unit) {
        return userRepository.isAuthedUser(callback)
    }
}