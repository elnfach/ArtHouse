package com.elnfach.arthouse.presentation

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elnfach.data.repository.NewsArticleRepositoryImpl
import com.elnfach.data.storage.NewsArticlesStorage
import com.elnfach.domain.usecase.GetNewsArticlesUseCase

class MainViewModelFactory : ViewModelProvider.Factory
{
    private val newsArticleRepository by lazy(LazyThreadSafetyMode.NONE)
    {
        NewsArticleRepositoryImpl(newsArticlesStorage = NewsArticlesStorage())
    }
    private val getNewsArticlesUseCase by lazy(LazyThreadSafetyMode.NONE)
    {
        GetNewsArticlesUseCase(newsArticlesRepository = newsArticleRepository)
    }
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getNewsArticlesUseCase = getNewsArticlesUseCase
        ) as T
    }
}