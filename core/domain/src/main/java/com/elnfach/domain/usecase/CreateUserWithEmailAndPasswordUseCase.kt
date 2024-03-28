package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.UserRepository
import com.elnfach.model.Resource
import com.elnfach.model.User

class CreateUserWithEmailAndPasswordUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(username: String, email: String, password: String, callback: (Resource<User?>) -> Unit) {
        return userRepository.createUserWithEmailAndPassword(username, email, password, callback)
    }
}