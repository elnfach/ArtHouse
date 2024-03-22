package com.elnfach.arthouse.presentation.deprecated.settings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager
import com.elnfach.model.ThemeSetting

class SettingsScreenViewModel(
    private val saveThemeSettingUseCase: com.elnfach.domain.usecase.SaveThemeSettingUseCase,
    private val getThemeSettingUseCase: com.elnfach.domain.usecase.GetThemeSettingUseCase
) : ViewModel() {
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
        saveThemeSettingUseCase.invoke(ThemeSetting(theme.name))
        Log.i("THEME | _theme after change", _theme.value!!)
    }
}