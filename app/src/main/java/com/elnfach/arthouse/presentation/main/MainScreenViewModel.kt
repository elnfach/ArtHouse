package com.elnfach.arthouse.presentation.main

import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.presentation.utils.Screen

class MainScreenViewModel : ViewModel() {
    private var _bottomItems = listOf(Screen.ForYou, Screen.Schedule, Screen.Profile)
    val bottomItems = _bottomItems
}