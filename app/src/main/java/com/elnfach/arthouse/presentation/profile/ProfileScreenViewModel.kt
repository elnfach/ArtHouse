package com.elnfach.arthouse.presentation.profile

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elnfach.arthouse.presentation.profile.sign_in.GoogleAuthUiClient
import com.elnfach.arthouse.presentation.profile.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.utils.Screen
import com.google.android.gms.auth.api.identity.Identity
import org.koin.androidx.compose.koinViewModel

class ProfileScreenViewModel(
    application: Application
) : ViewModel() {
    private val _state = MutableLiveData<ProfileState>()
    val state = _state

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = application.applicationContext,
            oneTapClient = Identity.getSignInClient(application.applicationContext)
        )
    }
}