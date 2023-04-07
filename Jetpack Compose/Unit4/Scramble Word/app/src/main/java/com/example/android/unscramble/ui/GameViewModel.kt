package com.example.android.unscramble.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel() : ViewModel() {
    private lateinit var currentWord: String
    private var usedWord: MutableSet<String> = mutableSetOf()

    // StateFlow
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    //ComposeState
    var userGuess by mutableStateOf("")
        private set

    /*
     StateFlow and ComposeState serve the same functions, is to observe data and trigger
     recomposition when the data is updated. But StateFlow have more advantage:
     1. It can direct use flow method like map, filter, ...
     2. It easily survive process death through SaveStateHandle, easily in here is the syntax.
     3. Because it is Kotlin library so it can be shared between KMM module.
     ! 1 and 2, ComposeState can be done but in a harder way(not that hard but it is need more
     code and specifically number 2 you also need to update the ComposeState and SaveStateHandle which is
     inconvenient and confuse.
     Conclude: Should use StateFlow.

     !How StateFlow and ComposeState work:
          + ComposeState expose the State right of the bat but StateFlow expose a StateFlow and we use collectAsState() to
          wrap all the value from the StateFlow and turn it into State
          + After that if Jetpack Compiler notice any change, it will make a recomposition.
          + uiState just a read-only version of _uiState(imagine like two variable point to the same memo). Because of
           that _uiState change lead to uiState change
    */
    init {
        resetGame()
    }

    private fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        if (usedWord.contains(currentWord)) {
            return pickRandomWordAndShuffle()
        } else {
            usedWord.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    private fun shuffleCurrentWord(input: String): String {
        val tempWord = input.toCharArray()
        tempWord.shuffle()
        while (String(tempWord).equals(input)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWord.clear()
        _uiState.value = GameUiState(currentScrambleWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessWord: String) {
        userGuess = guessWord
    }

    fun checkUserGuess() {
        if (currentWord.equals(userGuess, true)) {
            val updateScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updateScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    private fun updateGameState(updateScore: Int) {
        if (usedWord.size == MAX_NO_OF_WORDS) {
            _uiState.update { currentState ->
                currentState.copy(
                    score = updateScore,
                    isGuessWordWrong = false,
                    isGameOver = true,
                )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    score = updateScore,
                    currentScrambleWord = pickRandomWordAndShuffle(),
                    isGuessWordWrong = false,
                    currentWordCount = currentState.currentWordCount.inc(),
                )
            }
        }
    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }
}