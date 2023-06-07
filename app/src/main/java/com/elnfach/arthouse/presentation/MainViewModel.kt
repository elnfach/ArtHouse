package com.elnfach.arthouse.presentation

import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.ViewModel
import com.elnfach.data.repository.NewsArticleRepositoryImpl
import com.elnfach.domain.domain.usecase.GetNewsArticlesUseCase

class MainViewModel : ViewModel()
{
    init {
        Log.e("AAA", "VM created")
    }

    override fun onCleared() {
        Log.e("AAA", "VM cleared")
        super.onCleared()
    }
}