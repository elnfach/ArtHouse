package com.elnfach.arthouse.data.repository

interface FirebaseRepository {

    fun createUserWithEmailAndPassword(email: String, password: String)
    fun signInUserWithEmailAndPassword(email: String, password: String)
    fun signOutUser()
    fun isAuthedUser() : Boolean
}