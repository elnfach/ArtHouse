package com.elnfach.arthouse.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.domain.models.NewsArticle
import com.elnfach.domain.usecase.GetNewsArticlesUseCase

class MainViewModel(
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase
) : ViewModel()
{
    private val newsArticlesMutableLiveData = MutableLiveData<List<NewsArticle>>()
    val newsArticles: LiveData<List<NewsArticle>> = newsArticlesMutableLiveData
    init {
        Log.e("AAA", "VM created")
    }

    fun loadNewsArticles()
    {
        val newsArticle: List<NewsArticle> = getNewsArticlesUseCase.execute()
        newsArticlesMutableLiveData.value = newsArticle
    }

    override fun onCleared() {
        Log.e("AAA", "VM cleared")
        super.onCleared()
    }
}