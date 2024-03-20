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
            .addOnCompleteListener { task->
                if(task.isSuccessful)
                {
                    auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { sendEmailVerificationTask->
                        if(sendEmailVerificationTask.isSuccessful) {
                            Log.e("FIREBASE CREATE", "Send email verification")
                        } else {
                        }
                    }
                    Log.e("FIREBASE CREATE", task.result.user?.email.toString())
                }
                if(task.isComplete)
                {
                    Log.e("FIREBASE CREATE", "Is complete")
                }
                if(task.isCanceled)
                {
                    Log.e("FIREBASE CREATE", "Is canceled")
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