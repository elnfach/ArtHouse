package com.elnfach.arthouse.data.repository.impl

import android.content.Context
import android.util.Log
import com.elnfach.arthouse.data.repository.FirebaseRepository
import com.elnfach.arthouse.presentation.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl(
    private val  context: Context
) : FirebaseRepository {
    private val auth: FirebaseAuth = Firebase.auth
    override fun createUserWithEmailAndPassword(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { task->
                auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { sendEmailVerificationTask->
                    if(sendEmailVerificationTask.isSuccessful) {
                        Log.e("FIREBASE CREATE", "Send email verification")
                    }
                }
                Log.e("FIREBASE CREATE", task.user?.email.toString())
            }.addOnCompleteListener {
                Log.e("FIREBASE CREATE", "Is complete")
            }.addOnFailureListener {
                Log.e("FIREBASE CREATE", "Is canceled")
            }
    }

    override fun signInUserWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { task ->
                if (task != null) {
                    val user = task.user
                    Log.e("FIREBASE SIGN IN", user?.displayName.toString())
                }
            }.addOnFailureListener {
                Log.e("FIREBASE SIGN IN", "Failed")
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