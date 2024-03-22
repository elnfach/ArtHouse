package com.elnfach.firebase

import android.util.Log
import com.elnfach.domain.repository.firebase.FirebaseRepository
import com.elnfach.model.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseRepositoryImpl : FirebaseRepository {
    private val _auth: FirebaseAuth = Firebase.auth
    override fun createUserWithEmailAndPassword(email: String, password: String) {
        _auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { task->
                _auth.currentUser?.sendEmailVerification()?.addOnCompleteListener { sendEmailVerificationTask->
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
        _auth.signInWithEmailAndPassword(email, password)
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
        _auth.signOut()
    }

    override fun isAuthedUser(callback: (Resource<Boolean>) -> Unit) {
        _auth.currentUser
    }
}