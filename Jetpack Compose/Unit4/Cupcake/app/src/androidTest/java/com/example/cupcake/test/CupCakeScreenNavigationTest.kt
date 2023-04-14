package com.example.cupcake.test

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.cupcake.CupcakeApp
import com.example.cupcake.CupcakeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class CupCakeScreenNavigationTest {
    @get: Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>() // create a test rule

    /*To make sure that your app navigates to the correct place, you need to reference a TestNavHostController instance to
    check the navigation route of the nav host when the app takes actions to navigate.*/
    private lateinit var navController: TestNavHostController

    @Before // When a method is annotated with @Before, it runs before every method annotated with @Test
    fun setupCupCakeNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(
                ComposeNavigator()
            )
            CupcakeApp(navController = navController)
        }
    }

    @Test
    fun cupcakeNavHost_verifyStartDestination() {
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backTest = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backTest).assertDoesNotExist()
    }

    @Test
    fun cupcakeNavHost_clickOneCupcake_navigateToSelectFlavorScreen() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake).performClick()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    private fun navigateToFlavorScreenWithOneSelect() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.one_cupcake).performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.chocolate).performClick()
    }

    private fun getFormattedDate(): String {

        val calendar = Calendar.getInstance()

        calendar.add(java.util.Calendar.DATE, 1)

        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())

        return formatter.format(calendar.time)

    }

    private fun navigateToPickUpScreen() {
        navigateToFlavorScreenWithOneSelect()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).performClick()
    }

    private fun navigateToSummaryScreen() {
        navigateToPickUpScreen()
        composeTestRule.onNodeWithText(getFormattedDate()).performClick()
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.next).performClick()
    }

    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(com.example.cupcake.R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }

    private fun clickCancelButton() {
        composeTestRule.onNodeWithStringId(com.example.cupcake.R.string.cancel).performClick()
    }

    @Test
    fun cupcakeNavHost_performNavigateUpFromFlavorScreen_NavigateBackToStartScreen() {
        navigateToFlavorScreenWithOneSelect()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_clickCancelButtonFromFlavorScreen_NavigateBackToStartScreen() {
        navigateToFlavorScreenWithOneSelect()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_NavigateToPickUpScreen() {
        navigateToPickUpScreen()
    }

    @Test
    fun cupcakeNavHost_ClickUpButtonFromPickupScreen_NavigateToFlavorScreen() {
        navigateToPickUpScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(CupcakeScreen.Flavor.name)
    }

    @Test
    fun cupcakeNavHost_ClickCancelButtonFromPickUpScreen_NavigateToStartScreen() {
        navigateToPickUpScreen()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }

    @Test
    fun cupcakeNavHost_NavigateToSummaryScreen() {
        navigateToSummaryScreen()
    }

    @Test
    fun cupcakeNavHost_ClickCancelButtonFromSummaryScreen_NavigateToStartScreen() {
        navigateToSummaryScreen()
        clickCancelButton()
        navController.assertCurrentRouteName(CupcakeScreen.Start.name)
    }
}