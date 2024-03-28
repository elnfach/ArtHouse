package com.elnfach.arthouse.main

import androidx.lifecycle.ViewModel
import com.elnfach.arthouse.utils.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {
    private val _bottomItems = listOf(Screen.ForYou, Screen.Schedule, Screen.Profile)
    val bottomItems = _bottomItems
}