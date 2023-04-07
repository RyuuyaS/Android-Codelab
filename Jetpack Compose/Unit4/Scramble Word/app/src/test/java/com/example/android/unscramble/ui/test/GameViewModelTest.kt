package com.example.android.unscramble.ui.test

import com.example.android.unscramble.data.MAX_NO_OF_WORDS
import com.example.android.unscramble.data.SCORE_INCREASE
import com.example.android.unscramble.data.getUnscrambledWord
import com.example.android.unscramble.ui.GameViewModel
import junit.framework.TestCase.*
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GameViewModelTest {
    private val viewModel = GameViewModel()

    companion object {
        private const val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }

    /*
    You may wonder the following questions:
* Does it mean that the same instance of viewModel is reused for all the tests?
* Will it cause any issues? For example, what if the
 gameViewModel_Initialization_FirstWordLoaded test method executes after the
 gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset test method? Will the
 initialization test fail?

The answer to both questions is no. Test methods are executed in isolation to
avoid unexpected side effects from mutable test instance state. By default, before each
test method is executed, JUnit creates a new instance of the test class.
     */

    /*
           Success path: The success path tests - also known as happy path tests,
            focus on testing the functionality for a positive flow. A positive flow
            is a flow that has no exception or error conditions.
     */
    @Test // thingUnderTest_TriggerOfTest_ResultOfTest (how to name a test function)
    fun gameViewModel_CorrectWordGuessed_ScoreUpdateAndErrorFlagUnset() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambleWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()
        currentGameUiState = viewModel.uiState.value
        // Assert that checkUserGuess() method updates isGuessedWordWrong is updated correctly.
        assertFalse(currentGameUiState.isGuessWordWrong)
        // Assert that score is updated correctly.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
    }

    /*
        Error path: The error path tests focus on testing the functionality for a negative
        flow–that is, to check how the app responds to error conditions or invalid user input.
        It is quite challenging to determine all the possible error flows because there are
        lots of possible outcomes when intended behavior is not achieved.
    */
    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        val incorrectPlayerWord = "and"
        viewModel.updateUserGuess(incorrectPlayerWord)
        viewModel.checkUserGuess()
        val currentGameUiState = viewModel.uiState.value
        // Assert that score is unchanged
        assertEquals(0, currentGameUiState.score)
        // Assert that checkUserGuess() method updates isGuessedWordWrong correctly
        assertTrue(currentGameUiState.isGuessWordWrong)
    }

    /*
        Boundary case: A boundary case focuses on testing boundary conditions in the app.
        In the Unscramble app, a boundary is checking the UI state when the app loads and
        the UI state after the user plays a maximum number of words.
    */
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val gameUiState = viewModel.uiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.currentScrambleWord)
        // Assert that current word is scrambled.
        assertNotEquals(unScrambledWord, gameUiState.currentScrambleWord)
        // Assert that current word count is set to 1.
        assertTrue(gameUiState.currentWordCount == 1)
        // Assert that initially the score is 0.
        assertTrue(gameUiState.score == 0)
        // Assert that the wrong word guessed is false.
        assertFalse(gameUiState.isGuessWordWrong)
        // Assert that game is not over.
        assertFalse(gameUiState.isGameOver)
    }

    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = viewModel.uiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambleWord)
        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            viewModel.updateUserGuess(correctPlayerWord)
            viewModel.checkUserGuess()
            currentGameUiState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambleWord)
            // Assert that after each correct answer, score is updated correctly.
            assertEquals(expectedScore, currentGameUiState.score)
        }
        // Assert that after all questions are answered, the current word count is up-to-date.
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)
        // Assert that after 10 questions are answered, the game is over.
        assertTrue(currentGameUiState.isGameOver)
    }
}