package com.elnfach.firebase.repository

import com.elnfach.model.Resource

interface FirebaseRepository {
    fun createUserWithEmailAndPassword(email: String, password: String)
    fun signInUserWithEmailAndPassword(email: String, password: String)
    fun signOutUser()
    fun isAuthedUser(callback: (Resource<Boolean>) -> Unit)
}