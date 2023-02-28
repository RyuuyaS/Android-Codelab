package com.example.firstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.firstapp.ui.theme.FirstappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
//                    TaskManager()
//                    ComposeArticle()
                    ComposeQuadrant()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, from: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = "Hello $name!",
            fontSize = 36.sp,
        )
        Text(
            text = "From $from", fontSize = 24.sp, modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun BirthdayGreetingWithImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.androidparty)
    Box() {
        Image(
            painter = image, contentDescription = null, contentScale = ContentScale.Crop
        )
        Greeting(name = message, from = from)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FirstappTheme {
        BirthdayGreetingWithImage(stringResource(R.string.android), stringResource(R.string.thinh))
    }
}

@Composable
fun ComposeArticle() {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Column() {
        Image(
            painter = image,
            contentDescription = null,
        )
        Text(
            text = stringResource(id = R.string.title),
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = stringResource(id = R.string.intro),
            fontSize = 15.sp,
            modifier = Modifier.padding(
                start = 16.dp, end = 16.dp
            ),
            textAlign = TextAlign.Justify
        )
        Text(
            text = stringResource(id = R.string.detail),
            fontSize = 15.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComposeArticle() {
    FirstappTheme {
        ComposeArticle()
    }
}

@Composable
fun TaskManager(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.ic_task_completed)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = image,
            contentDescription = null,
        )
        Text(
            text = stringResource(id = R.string.finalMessage),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
        )
        Text(
            text = stringResource(id = R.string.cheer),
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTaskManager() {
    FirstappTheme {
        TaskManager()
    }
}

@Composable
fun ContentEachPart(title: String, content: String, color: Color, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(color)
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = content,
            textAlign = TextAlign.Justify,
        )
    }
}

@Composable
fun ComposeQuadrant() {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            ContentEachPart(
                title = stringResource(id = R.string.textComposable),
                content = stringResource(id = R.string.textComposableContent),
                color = Color.Green,
                modifier = Modifier.weight(0.5f)
            )
            ContentEachPart(
                title = stringResource(id = R.string.imageComposable),
                content = stringResource(id = R.string.imageComposableContent),
                color = Color.Yellow,
                modifier = Modifier.weight(0.5f)
            )
        }
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            ContentEachPart(
                title = stringResource(id = R.string.rowComposable),
                content = stringResource(id = R.string.rowComposableContent),
                color = Color.Cyan,
                modifier = Modifier.weight(0.5f),
            )
            ContentEachPart(
                title = stringResource(id = R.string.columnComposable),
                content = stringResource(id = R.string.columnComposableContent),
                color = Color.LightGray,
                modifier = Modifier.weight(0.5f),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComposeQuadrant() {
    FirstappTheme {
        ComposeQuadrant()
    }
}

