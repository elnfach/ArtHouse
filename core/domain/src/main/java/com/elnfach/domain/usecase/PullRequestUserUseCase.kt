package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.UserRepository
import com.elnfach.model.Resource
import com.elnfach.model.User

class PullRequestUserUseCase(
    private val userRepository: UserRepository
){
    operator fun invoke(callback: (Resource<User?>) -> Unit)
    {
        return userRepository.pullRequest(callback)
    }
}