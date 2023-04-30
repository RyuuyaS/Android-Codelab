package com.example.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.data.DataSource
import com.example.mycity.model.Recommendation
import com.example.mycity.ui.screen.CategoryScreen
import com.example.mycity.ui.screen.DetailScreen
import com.example.mycity.ui.screen.HomeScreen
import com.example.mycity.ui.state.AppViewModel
import com.example.mycity.ui.state.Screen
import com.example.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyCityApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = viewModel()
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentScreen = Screen.valueOf(
        currentBackStack?.destination?.route ?: Screen.HomeScreen.name
    )
    val index = viewModel.appState.collectAsState().value
    val recommendedList = listOfNotNull(DataSource.abc[index.categoryIndex])[0]
    Scaffold(
        topBar = {
            AppBar(
                currentScreen = currentScreen,
                categoryIndex = index.categoryIndex,
                detailIndex = index.detailIndex,
                recommendedList = recommendedList,
                navController = navController,
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        NavHost(
            startDestination = Screen.HomeScreen.name,
            navController = navController,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.HomeScreen.name) {
                HomeScreen(
                    categoryList = DataSource.categoryList,
                    onClick = { categoryIndex ->
                        viewModel.updateCategoryIndex(categoryIndex)
                        navController.navigate(Screen.CategoryScreen.name)
                    }
                )
            }
            composable(Screen.CategoryScreen.name) {
                CategoryScreen(
                    recommendedList = recommendedList,
                    onClick = { detailIndex ->
                        viewModel.updateDetailIndex(detailIndex)
                        navController.navigate(Screen.DetailScreen.name)
                    }
                )
            }
            composable(Screen.DetailScreen.name) {
                DetailScreen(
                    name = recommendedList[index.detailIndex].name,
                    image = recommendedList[index.detailIndex].image,
                    description = recommendedList[index.detailIndex].description,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    currentScreen: Screen,
    categoryIndex: Int,
    detailIndex: Int,
    recommendedList: List<Recommendation>,
    onClick: () -> Unit,
    navController: NavHostController
) {
    lateinit var title: String
    if (currentScreen.name == Screen.CategoryScreen.name) {
        title = stringResource(id = DataSource.categoryList[categoryIndex].name) + " Recommended"
    } else if (currentScreen.name == Screen.DetailScreen.name) {
        title = stringResource(id = recommendedList[detailIndex].name)
    } else {
        title = "Category"
    }
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 30.sp,
                fontWeight =
                FontWeight.Bold,
                lineHeight = 40.sp
            )
        },
        modifier = Modifier,
        navigationIcon = {
            if (navController.previousBackStackEntry != null) {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    )
    /*    Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
            Text(
                text = title,
                fontSize = 30.sp,
                fontWeight =
                FontWeight.Bold,
                lineHeight = 40.sp
                )
        } */
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCityTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MyCityApp()
        }
    }
}