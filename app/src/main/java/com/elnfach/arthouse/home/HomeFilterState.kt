package com.elnfach.arthouse.home

data class HomeFilterState(
    val value: String = FilterNews.PUBLISHED.name.lowercase()
)
