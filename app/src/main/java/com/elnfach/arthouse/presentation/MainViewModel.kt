package com.elnfach.arthouse.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.domain.models.NewsArticle
import com.elnfach.domain.usecase.GetNewsArticlesUseCase

class MainViewModel(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase
) : ViewModel()
{
    val newsArticles = MutableLiveData<List<NewsArticle>>()
    init {
        Log.e("AAA", "VM created")
    }

    fun loadNewsArticles()
    {
        val newsArticle: List<NewsArticle> = getNewsArticlesUseCase.execute()
        newsArticles.value = newsArticle
    }

    override fun onCleared() {
        Log.e("AAA", "VM cleared")
        super.onCleared()
    }
}