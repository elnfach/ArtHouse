package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.UserRepository

class SignOutUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke() {
        return userRepository.signOutUser()
    }
}