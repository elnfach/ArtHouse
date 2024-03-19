package com.elnfach.arthouse.presentation.profile

import android.app.Application
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.elnfach.arthouse.domain.usecase.GetThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.SaveThemeSettingUseCase
import com.elnfach.arthouse.presentation.deprecated.settings.sign_in.GoogleAuthUiClient
import com.elnfach.arthouse.presentation.deprecated.settings.sign_in.SignInViewModel
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager
import com.elnfach.arthouse.presentation.utils.Screen
import com.google.android.gms.auth.api.identity.Identity
import org.koin.androidx.compose.koinViewModel

class ProfileScreenViewModel(
    application: Application,
    private val saveThemeSettingUseCase: SaveThemeSettingUseCase,
    private val getThemeSettingUseCase: GetThemeSettingUseCase
) : ViewModel() {
    private val _state = MutableLiveData<ProfileState>()
    val state = _state

    private val _theme = MutableLiveData(getThemeSettingUseCase.invoke().theme)
    val theme: LiveData<String> = _theme

    fun onThemeChanged(theme: Theme) {
        Log.i("THEME | theme", theme.name)
        Log.i("THEME | _theme", _theme.value!!)
        when (theme) {
            Theme.DEFAULT -> _theme.value = Theme.DEFAULT.name
            Theme.LIGHT -> _theme.value = Theme.LIGHT.name
            Theme.DARK -> _theme.value = Theme.DARK.name
        }
        ThemeManager.artHouseTheme = theme
        saveThemeSettingUseCase.invoke(theme)
        Log.i("THEME | _theme after change", _theme.value!!)
    }

    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = application.applicationContext,
            oneTapClient = Identity.getSignInClient(application.applicationContext)
        )
    }
}