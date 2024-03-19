package com.elnfach.arthouse.presentation.profile

import android.app.Application
import android.util.Log
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.R
import com.elnfach.arthouse.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.elnfach.arthouse.domain.usecase.GetThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.SaveThemeSettingUseCase
import com.elnfach.arthouse.presentation.profile.detailsPage.components.dataclass.AppearanceRadioButtonItem
import com.elnfach.arthouse.presentation.profile.detailsPage.components.dataclass.LanguageRadioButtonItem
import com.elnfach.arthouse.presentation.profile.detailsPage.components.dataclass.NotificationsRadioButtonItem
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Language
import com.elnfach.arthouse.presentation.profile.detailsPage.components.enums.Notifications
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager

class ProfileScreenViewModel(
    application: Application,
    private val createUserWithEmailAndPasswordUseCase: CreateUserWithEmailAndPasswordUseCase,
    private val saveThemeSettingUseCase: SaveThemeSettingUseCase,
    private val getThemeSettingUseCase: GetThemeSettingUseCase
) : ViewModel() {

    private val _theme = MutableLiveData(getThemeSettingUseCase.invoke().theme)
    val theme: LiveData<String> = _theme

    private val _notifications = MutableLiveData(getThemeSettingUseCase.invoke().theme)
    val notifications: LiveData<String> = _notifications

    private val _language = MutableLiveData(getThemeSettingUseCase.invoke().theme)
    val language: LiveData<String> = _language

    private val _appearanceOptions = listOf(
        AppearanceRadioButtonItem(R.string.system_default, Theme.DEFAULT),
        AppearanceRadioButtonItem(R.string.light_theme, Theme.LIGHT),
        AppearanceRadioButtonItem(R.string.dark_theme, Theme.DARK)
    )
    val appearanceOptions = _appearanceOptions

    private val _notificationsOptions = listOf(
        NotificationsRadioButtonItem(R.string.notifications_off, Notifications.OFF),
        NotificationsRadioButtonItem(R.string.notifications_only_important, Notifications.ONLY_IMPORTANT),
        NotificationsRadioButtonItem(R.string.notifications_all, Notifications.ALL)
    )
    val notificationsOptions = _notificationsOptions

    private val _languageOptions = listOf(
        LanguageRadioButtonItem(R.string.system_default, Language.DEFAULT),
        LanguageRadioButtonItem(R.string.english_language, Language.ENGLISH),
        LanguageRadioButtonItem(R.string.russian_language, Language.RUSSIAN)
    )
    val languageOptions = _languageOptions

    fun createUser(email: String, password: String)
    {
        createUserWithEmailAndPasswordUseCase.invoke(email, password)
    }

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
}