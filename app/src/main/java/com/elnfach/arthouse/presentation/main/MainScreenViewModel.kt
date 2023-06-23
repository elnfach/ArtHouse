package com.elnfach.arthouse.presentation.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.presentation.sign_in.GoogleAuthUiClient
import com.elnfach.arthouse.presentation.utils.Screen
import com.google.android.gms.auth.api.identity.Identity

class MainScreenViewModel(application: Application) : ViewModel() {

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = application.applicationContext,
            oneTapClient = Identity.getSignInClient(application.applicationContext)
        )
    }

    private var _bottomItems = listOf(Screen.ForYou, Screen.Schedule, Screen.Profile)
    val bottomItems = _bottomItems
}