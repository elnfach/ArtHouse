package com.elnfach.arthouse.data.repository

import com.elnfach.arthouse.presentation.utils.Resource

interface FirebaseRepository {

    fun createUserWithEmailAndPassword(email: String, password: String)
    fun signInUserWithEmailAndPassword(email: String, password: String)
    fun signOutUser()
    fun isAuthedUser(callback: (Resource<Boolean>) -> Unit)
}