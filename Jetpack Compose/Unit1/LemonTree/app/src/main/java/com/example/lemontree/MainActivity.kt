package com.example.lemontree

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemontree.ui.theme.LemonTreeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonTreeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@Composable
fun LemonApp() {
    var index by rememberSaveable {
        mutableStateOf(1)
    }
    var squeezeNumber by rememberSaveable {
        mutableStateOf(0)
    }
    val content = stringResource(id = getContent(index))
    val painter = painterResource(id = getImage(index))
    val contentDescription = stringResource(id = getContentDescription(index))
    LemonActivity(content = content, painter = painter, contentDescription = contentDescription) {
        when (index) {
            1 -> {
                squeezeNumber = (1..6).random()
                index++
            }

            2 -> {
                if (squeezeNumber > 0) {
                    squeezeNumber--
                } else {
                    index++
                }
            }

            3 -> index++
            4 -> {
                index = 1
                squeezeNumber = 0
            }
        }
    }
}

@Composable
fun LemonActivity(
    content: String,
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
    ) {
        Text(
            text = content,
            fontSize = 18.sp,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .border(2.dp, Color(105, 205, 216))
                .padding(16.dp)
                .clickable {
                    onClick()
                }
        )
    }
}

@Preview
@Composable
fun Preview() {
    LemonTreeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LemonApp()
        }
    }
}

private fun getImage(index: Int): Int {
    return when (index) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
}

private fun getContent(index: Int): Int {
    return when (index) {
        1 -> R.string.select_a_lemon
        2 -> R.string.squeeze_a_lemon
        3 -> R.string.drink_lemonade
        else -> R.string.restart
    }
}

private fun getContentDescription(index: Int): Int {
    return when (index) {
        1 -> R.string.select_content
        2 -> R.string.squeeze_content
        3 -> R.string.drink_content
        else -> R.string.restart_content
    }
}
