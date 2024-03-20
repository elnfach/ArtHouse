package com.elnfach.arthouse.presentation.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.R
import com.elnfach.arthouse.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.elnfach.arthouse.domain.usecase.GetLanguageSettingUseCase
import com.elnfach.arthouse.domain.usecase.GetNotificationsSettingUseCase
import com.elnfach.arthouse.domain.usecase.GetThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.IsAuthedUserUseCase
import com.elnfach.arthouse.domain.usecase.SaveLanguageSettingUseCase
import com.elnfach.arthouse.domain.usecase.SaveNotificationsSettingUseCase
import com.elnfach.arthouse.domain.usecase.SaveThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.SignOutUserUseCase
import com.elnfach.arthouse.presentation.LanguageManager
import com.elnfach.arthouse.presentation.NotificationsManager
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
    private val isAuthedUserUseCase: IsAuthedUserUseCase,
    private val signOutUserUseCase: SignOutUserUseCase,
    private val saveThemeSettingUseCase: SaveThemeSettingUseCase,
    private val getThemeSettingUseCase: GetThemeSettingUseCase,
    private val saveLanguageSettingUseCase: SaveLanguageSettingUseCase,
    private val getLanguageSettingUseCase: GetLanguageSettingUseCase,
    private val saveNotificationsSettingUseCase: SaveNotificationsSettingUseCase,
    private val getNotificationsSettingUseCase: GetNotificationsSettingUseCase
) : ViewModel() {

    private val _theme = MutableLiveData(getThemeSettingUseCase.invoke().theme)
    val theme: LiveData<String> = _theme

    private val _notifications = MutableLiveData(getNotificationsSettingUseCase.invoke().notifications)
    val notifications: LiveData<String> = _notifications

    private val _language = MutableLiveData(getLanguageSettingUseCase.invoke().language)
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

    fun signOutUser()
    {
        signOutUserUseCase.invoke()
    }

    fun isAuthedUser() : Boolean
    {
        return isAuthedUserUseCase.invoke()
    }

    fun onLanguageChanged(language: Language)
    {
        when(language)
        {
            Language.DEFAULT -> _language.value = Language.DEFAULT.name
            Language.ENGLISH -> _language.value = Language.ENGLISH.name
            Language.RUSSIAN -> _language.value = Language.RUSSIAN.name
        }
        LanguageManager.language = language
        saveLanguageSettingUseCase.invoke(language)
    }

    fun onNotificationsSettingChanged(notifications: Notifications)
    {
        when(notifications)
        {
            Notifications.ALL               -> _notifications.value = Notifications.ALL.name
            Notifications.ONLY_IMPORTANT    -> _notifications.value = Notifications.ONLY_IMPORTANT.name
            Notifications.OFF               -> _notifications.value = Notifications.OFF.name
        }
        NotificationsManager.notifications = notifications
        saveNotificationsSettingUseCase.invoke(notifications)
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