package com.example.mycity.ui.state

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _appState = MutableStateFlow<AppState>(AppState())
    val appState = _appState.asStateFlow()
    fun updateCategoryIndex(
        indexClick: Int
    ) {
        _appState.update {
            it.copy(
                categoryIndex = indexClick
            )
        }
    }

    fun updateDetailIndex(indexClick: Int) {
        _appState.update {
            it.copy(
                detailIndex = indexClick
            )
        }
    }
}