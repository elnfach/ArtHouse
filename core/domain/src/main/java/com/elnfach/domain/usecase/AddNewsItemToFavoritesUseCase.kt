package com.elnfach.domain.usecase

import com.elnfach.domain.repository.firebase.UserRepository
import com.elnfach.model.Resource

class AddNewsItemToFavoritesUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(id: String, callback: (Resource<Boolean>) -> Unit)
    {
        userRepository.addNewsItemToFavorites(id, callback)
    }
}