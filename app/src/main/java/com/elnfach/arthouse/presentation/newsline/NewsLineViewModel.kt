package com.elnfach.arthouse.presentation.newsline

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.elnfach.domain.models.NewsArticle
import com.elnfach.domain.usecase.GetNewsArticleByIdUseCase
import com.elnfach.domain.usecase.GetNewsArticlesUseCase

class NewsLineViewModel (
    private val getNewsArticlesUseCase: GetNewsArticlesUseCase,
    private val getNewsArticleByIdUseCase: GetNewsArticleByIdUseCase
) : ViewModel() {
    private val newsArticlesMutableLiveData = MutableLiveData<List<NewsArticle>>()
    private var newsArticleMutableLiveData = MutableLiveData<NewsArticle>()
    val newsArticles: LiveData<List<NewsArticle>> = newsArticlesMutableLiveData
    val newsArticle: LiveData<NewsArticle> = newsArticleMutableLiveData

    private var state: MutableState<Int> = mutableIntStateOf(0)
    @Composable
    fun SaveId(id: Int)
    {
        state = remember { mutableIntStateOf(id) }
    }
    fun loadNewsArticles()
    {
        val newsArticle: List<NewsArticle> = getNewsArticlesUseCase.execute()
        newsArticlesMutableLiveData.value = newsArticle
    }
    fun loadNewsArticleById()
    {
        val newsArticle: NewsArticle = getNewsArticleByIdUseCase.execute(state.value)
        newsArticleMutableLiveData = MutableLiveData(newsArticle)
    }
    override fun onCleared() {
        super.onCleared()
    }
}