package com.elnfach.arthouse.data.repository.impl

import android.util.Log
import com.elnfach.arthouse.data.repository.FirebaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl : FirebaseRepository {
    private val auth: FirebaseAuth = Firebase.auth
    override fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { task ->
                if (task != null) {
                    Log.e("FIREBASE CREATE", task.user?.displayName.toString())
                } else {
                    Log.e("FIREBASE CREATE", "Failed")
                }
            }
    }

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Log.e("FIREBASE SIGN IN", user?.displayName.toString())
                } else {
                    Log.e("FIREBASE SIGN IN", "Failed")
                }
            }
    }

    override fun signOutUser() {
        auth.signOut()
    }

    override fun isAuthedUser(): Boolean {
        val currentUser = auth.currentUser
        return currentUser != null
    }
}