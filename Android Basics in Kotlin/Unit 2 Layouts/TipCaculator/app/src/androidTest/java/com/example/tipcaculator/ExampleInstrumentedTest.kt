package com.example.tipcaculator

import androidx.test.espresso.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get: Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun caculate20PercentTip() {
        onView(withId(R.id.etInputCost)).perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btnCalculate)).perform(click())
        onView(withId(R.id.tvTipAmount)).check(matches(withText(containsString("$10.00"))))
    }

    fun caculate18PercentTip() {
        onView(withId(R.id.etInputCost)).perform(typeText("36.00"))
            .perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.rbEighteenPercent)).perform(click())
        onView(withId(R.id.btnCalculate)).perform(click())
        onView(withId(R.id.tvTipAmount)).check(matches(withText(containsString("$6.48"))))
    }


    fun caculate15PercentTip() {
        onView(withId(R.id.etInputCost)).perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.rbFifteenPercent)).perform(click())
        onView(withId(R.id.btnCalculate)).perform(click())
        onView(withId(R.id.tvTipAmount)).check(matches(withText(containsString("$7.50"))))
    }
}