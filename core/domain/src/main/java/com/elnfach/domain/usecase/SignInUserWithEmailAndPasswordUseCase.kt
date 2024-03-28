package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.UserRepository
import com.elnfach.model.Resource
import com.elnfach.model.User

class SignInUserWithEmailAndPasswordUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String, password: String, callback: (Resource<User?>) -> Unit) {
        return userRepository.signInUserWithEmailAndPassword(email, password, callback)
    }
}