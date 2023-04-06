package com.example.coroutines.ui.State

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.reflect.KProperty

class AppViewModel(): ViewModel()
{
    var uiState by mutableStateOf(AppUiState())
        private set

    private var fetchJob: Job? = null

    fun fetchArticles(category: String) {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch {
            try {
                val newsItems = listOf<String>("a")
                uiState = uiState.copy()
            } catch (ioe: IOException) {
                // Handle the error and notify the UI when appropriate.
                val messages = listOf<String>("a")
                uiState = uiState.copy(userMessages = messages)
            }
        }
    }
}


