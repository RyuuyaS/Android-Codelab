/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import android.graphics.drawable.Icon
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderPreview
import com.example.lunchtray.ui.StartOrderScreen
import com.example.lunchtray.ui.theme.Purple700

// TODO: Screen enum
enum class Screen(@StringRes val title: Int) {
    StartMenu(R.string.app_name),
    EntreeMenu(R.string.choose_entree),
    SideDishMenu(R.string.choose_side_dish),
    AccompanimentMenu(R.string.choose_accompaniment),
    CheckoutMenu(R.string.order_checkout)
}

// TODO: AppBar
@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun TopAppBar(
    @StringRes name: Int,
    navigateUp: () -> Unit,
    canNavigateBack: Boolean,
) {
    Row(
        modifier = Modifier
            .background(Purple700)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (canNavigateBack) {
            Button(
                onClick = navigateUp,
                colors = ButtonDefaults.buttonColors(Purple700),
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
        Spacer(Modifier.size(16.dp))
        Text(
            text = stringResource(id = name), color = Color.White, fontSize = 24.sp
        )
    }
}


@RequiresApi(Build.VERSION_CODES.M)
@Composable
fun LunchTrayApp(modifier: Modifier = Modifier) {
    // TODO: Create Controller and initialization
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        backStackEntry?.destination?.route ?: Screen.StartMenu.name
    )
    // Create ViewModel
    val viewModel: OrderViewModel = viewModel()

    Scaffold(topBar = {
        // TODO: AppBar
        TopAppBar(
            canNavigateBack = navController.previousBackStackEntry != null,
            name = currentScreen.title,
            navigateUp = {
                navController.popBackStack()
            }
        )
    }) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = Screen.StartMenu.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.StartMenu.name) {
                StartOrderScreen(onStartOrderButtonClicked = {
                    navController.navigate(Screen.EntreeMenu.name)
                })
            }

            composable(Screen.EntreeMenu.name) {
                EntreeMenuScreen(options = DataSource.entreeMenuItems, onCancelButtonClicked = {
                    navController.popBackStack()
                }, onNextButtonClicked = {
                    navController.navigate(Screen.SideDishMenu.name)
                }, onSelectionChanged = {
                    viewModel.updateEntree(it)
                })
            }

            composable(Screen.SideDishMenu.name) {
                SideDishMenuScreen(options = DataSource.sideDishMenuItems, onCancelButtonClicked = {
                    navController.popBackStack()
                }, onNextButtonClicked = {
                    navController.navigate(Screen.AccompanimentMenu.name)
                }, onSelectionChanged = {
                    viewModel.updateSideDish(it)
                })
            }

            composable(Screen.AccompanimentMenu.name) {
                AccompanimentMenuScreen(options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        navController.popBackStack()
                    },
                    onNextButtonClicked = {
                        navController.navigate(Screen.CheckoutMenu.name)
                    },
                    onSelectionChanged = {
                        viewModel.updateAccompaniment(it)
                    })
            }

            composable(Screen.CheckoutMenu.name) {
                CheckoutScreen(orderUiState = uiState, onNextButtonClicked = {
                    navController.popBackStack(route = Screen.StartMenu.name, inclusive = false)
                }, onCancelButtonClicked = { navController.popBackStack() })
            }
        }
    }
}
