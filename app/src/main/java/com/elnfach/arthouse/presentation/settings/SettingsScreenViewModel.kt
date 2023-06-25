package com.elnfach.arthouse.presentation.settings

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.domain.usecase.GetThemeSettingUseCase
import com.elnfach.arthouse.domain.usecase.SaveThemeSettingUseCase
import com.elnfach.arthouse.model.ThemeSetting
import com.elnfach.arthouse.presentation.ui.theme.Theme
import com.elnfach.arthouse.presentation.ui.theme.ThemeManager

class SettingsScreenViewModel(
    private val saveThemeSettingUseCase: SaveThemeSettingUseCase,
    private val getThemeSettingUseCase: GetThemeSettingUseCase
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
        saveThemeSettingUseCase.invoke(theme)
        Log.i("THEME | _theme after change", _theme.value!!)
    }
}