package com.elnfach.arthouse.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.R
import com.elnfach.arthouse.profile.details.FavoritesState
import com.elnfach.arthouse.profile.details.components.dataclass.AppearanceRadioButtonItem
import com.elnfach.arthouse.profile.details.components.dataclass.LanguageRadioButtonItem
import com.elnfach.arthouse.profile.details.components.dataclass.NotificationsRadioButtonItem
import com.elnfach.arthouse.profile.details.components.enums.Language
import com.elnfach.arthouse.profile.details.components.enums.Notifications
import com.elnfach.arthouse.ui.theme.Theme
import com.elnfach.arthouse.utils.managers.LanguageManager
import com.elnfach.arthouse.utils.managers.NotificationsManager
import com.elnfach.arthouse.utils.managers.ThemeManager
import com.elnfach.arthouse.utils.managers.UserManager
import com.elnfach.domain.usecase.CreateUserWithEmailAndPasswordUseCase
import com.elnfach.domain.usecase.GetAllFavoritesUseCase
import com.elnfach.domain.usecase.GetIsAuthedUserUseCase
import com.elnfach.domain.usecase.GetLanguageSettingUseCase
import com.elnfach.domain.usecase.GetNewsItemByIdUseCase
import com.elnfach.domain.usecase.GetNotificationsSettingUseCase
import com.elnfach.domain.usecase.GetThemeSettingUseCase
import com.elnfach.domain.usecase.IsAuthedUserUseCase
import com.elnfach.domain.usecase.PullRequestUserUseCase
import com.elnfach.domain.usecase.SaveAuthStatusUseCase
import com.elnfach.domain.usecase.SaveLanguageSettingUseCase
import com.elnfach.domain.usecase.SaveNotificationsSettingUseCase
import com.elnfach.domain.usecase.SaveThemeSettingUseCase
import com.elnfach.domain.usecase.SignInUserWithEmailAndPasswordUseCase
import com.elnfach.domain.usecase.SignOutUserUseCase
import com.elnfach.model.LanguageOption
import com.elnfach.model.NotificationsOption
import com.elnfach.model.Resource
import com.elnfach.model.ThemeOption
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val createUserWithEmailAndPasswordUseCase: CreateUserWithEmailAndPasswordUseCase,
    private val signInUserWithEmailAndPasswordUseCase: SignInUserWithEmailAndPasswordUseCase,
    private val pullRequestUserUseCase: PullRequestUserUseCase,
    private val isAuthedUserUseCase: IsAuthedUserUseCase,
    private val signOutUserUseCase: SignOutUserUseCase,
    private val saveThemeSettingUseCase: SaveThemeSettingUseCase,
    private val getThemeSettingUseCase: GetThemeSettingUseCase,
    private val saveLanguageSettingUseCase: SaveLanguageSettingUseCase,
    private val getLanguageSettingUseCase: GetLanguageSettingUseCase,
    private val saveNotificationsSettingUseCase: SaveNotificationsSettingUseCase,
    private val getNotificationsSettingUseCase: GetNotificationsSettingUseCase,
    private val getIsAuthedUserUseCase: GetIsAuthedUserUseCase,
    private val saveAuthStatusUseCase: SaveAuthStatusUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val getNewsItemByIdUseCase: GetNewsItemByIdUseCase
) : ViewModel() {
    private val _user = mutableStateOf(ProfileState())
    val user: State<ProfileState> = _user

    private val _username = MutableLiveData<String>(_user.value.value?.displayName)
    val username: LiveData<String> = _username

    private val _theme = MutableLiveData(getThemeSettingUseCase.invoke().value)
    val theme: LiveData<String> = _theme

    private val _notifications = MutableLiveData(getNotificationsSettingUseCase.invoke().value)
    val notifications: LiveData<String> = _notifications

    private val _language = MutableLiveData(getLanguageSettingUseCase.invoke().value)
    val language: LiveData<String> = _language

    private val _userStorage = MutableLiveData(getIsAuthedUserUseCase.invoke())
    val userStorage: LiveData<String> = _userStorage

    private val _favorites = mutableStateOf(FavoritesState())
    val favorites: State<FavoritesState> = _favorites

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

    init {
        isAuthedUser()
        pullRequest()
    }

    fun update()
    {
        pullRequest()
    }

    private fun pullRequest()
    {
        pullRequestUserUseCase.invoke { result ->
            when(result)
            {
                is Resource.Success -> {
                    _user.value = ProfileState(value = result.data)
                    val data = result.data
                    if(result.data != null)
                    {
                        _username.value = result.data?.displayName
                    }
                }
                is Resource.Error ->
                {
                    _user.value = ProfileState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _user.value = ProfileState(loading = true)
                }
            }
        }
    }

    fun createUser(username: String, email: String, password: String, callback: (Boolean) -> Unit)
    {
        createUserWithEmailAndPasswordUseCase.invoke(username, email, password, callback = { result ->
            when(result)
            {
                is Resource.Success -> {
                    Log.e("PROFILE","1")
                    _user.value = ProfileState(value = result.data)
                    if(result.data != null)
                        callback(true)
                }
                is Resource.Error -> {
                    _user.value = ProfileState(error = result.message ?: "An unexpected error occurred")
                    callback(false)
                }
                is Resource.Loading -> {
                    _user.value = ProfileState(loading = true)
                }
            }
        })
        isAuthedUser()
        pullRequest()
    }

    fun signInUser(email: String, password: String, callback: (Boolean) -> Unit)
    {
        signInUserWithEmailAndPasswordUseCase.invoke(email, password, callback = { result ->
            when(result)
            {
                is Resource.Success -> {
                    _user.value = ProfileState(value = result.data)
                    if(result.data != null)
                        callback(true)
                }
                is Resource.Error -> {
                    _user.value = ProfileState(error = result.message ?: "An unexpected error occurred")
                    callback(false)
                }
                is Resource.Loading -> {
                    _user.value = ProfileState(loading = true)
                }
            }
        })
        isAuthedUser()
        pullRequest()
    }

    fun signOutUser()
    {
        signOutUserUseCase.invoke()
        isAuthedUser()
    }

    private fun isAuthedUser()
    {
        isAuthedUserUseCase.invoke(callback = {result ->
            when(result){
                is Resource.Success -> {
                    Log.e("PROFILE","7")
                    when(result.data)
                    {
                        true -> _userStorage.value = true.toString()
                        false -> _userStorage.value = false.toString()
                        null -> _userStorage.value = false.toString()
                    }
                    UserManager.status = result.data == true
                    saveAuthStatusUseCase.invoke(_userStorage.value.toString())
                }
                is Resource.Error -> {
                    _user.value = ProfileState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _user.value = ProfileState(loading = true)
                }
            }
        })
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
        saveLanguageSettingUseCase.invoke(LanguageOption(language.name))
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
        saveNotificationsSettingUseCase.invoke(NotificationsOption(notifications.name))
    }

    fun onThemeChanged(theme: Theme) {
        when (theme) {
            Theme.DEFAULT -> _theme.value = Theme.DEFAULT.name
            Theme.LIGHT -> _theme.value = Theme.LIGHT.name
            Theme.DARK -> _theme.value = Theme.DARK.name
        }
        ThemeManager.artHouseTheme = theme
        saveThemeSettingUseCase.invoke(ThemeOption(theme.name))
    }

    fun getAllFavorites()
    {
        getAllFavoritesUseCase.invoke { result ->
            when(result)
            {
                is Resource.Success -> {
                    _favorites.value = FavoritesState(news = result.data)
                }
                is Resource.Error -> {
                    _favorites.value = FavoritesState(error = result.message.toString())
                }
                is Resource.Loading -> {
                    _favorites.value = FavoritesState(loading = true)
                }
            }
        }
    }
}