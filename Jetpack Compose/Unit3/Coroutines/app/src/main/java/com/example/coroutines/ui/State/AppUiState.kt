package com.example.coroutines.ui.State

data class AppUiState(
    val isSignedIn: Boolean = false,
    val isPremium: Boolean = false,
    val newsItems: List<String> = listOf(),
    val userMessages: List<String> = listOf()
)
