package com.elnfach.arthouse.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.data.repository.FirebaseRepository
import com.elnfach.arthouse.domain.usecase.IsAuthedUserUseCase
import com.elnfach.arthouse.domain.usecase.SignOutUserUseCase
import com.elnfach.arthouse.presentation.utils.Screen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainScreenViewModel(
) : ViewModel() {
    private val _bottomItems = listOf(Screen.ForYou, Screen.Schedule, Screen.Profile)
    val bottomItems = _bottomItems
}