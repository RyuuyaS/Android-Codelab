package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

/* the <A: ComponentActivity just a reference to type name A to ComponentActivity so that in the ActivityScenarioRule does
not have to write two time ComponentActivity again, the two function below is the Same but in the below ComponentActivity
is duplicate, which make it seem not robust
*/
fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))

//fun AndroidComposeTestRule<ActivityScenarioRule<ComponentActivity>, ComponentActivity>.onNodeWithStringId(
//    @StringRes id: Int
//): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))
