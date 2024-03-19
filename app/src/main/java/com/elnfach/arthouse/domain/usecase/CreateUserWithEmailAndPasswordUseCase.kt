package com.elnfach.arthouse.domain.usecase

import com.elnfach.arthouse.data.repository.FirebaseRepository
import com.elnfach.arthouse.data.repository.NewsRepository
import com.elnfach.arthouse.model.NewsArticle
import com.elnfach.arthouse.presentation.utils.Resource
import kotlinx.coroutines.flow.Flow

class CreateUserWithEmailAndPasswordUseCase(
    private val repository: FirebaseRepository
) {
    operator fun invoke(email: String, password: String) {
        return repository.createUserWithEmailAndPassword(email, password)
    }
}