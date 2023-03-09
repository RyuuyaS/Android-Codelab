package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceActivity()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceActivity() {
    var index by remember {
        mutableStateOf(0)
    }
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .wrapContentSize()
                .weight(2f),
            shadowElevation = 9.dp,
            border = BorderStroke(2.dp, Color.Gray)
        ) {
            ArtSpaceImage(index)
        }
        Surface(
            Modifier
                .weight(2f)
                .wrapContentSize(),
            shadowElevation = 9.dp
        ) {
            ArtSpaceInfo(index)
        }
        Spacer(
            Modifier.height(15.dp)
        )
        Surface(
            Modifier
                .wrapContentSize(),
        ) {
            FunctionButton(
                prevClick = {
                    index--
                    if (index < 1) {
                        index = 6
                    }
                },
                nextClick = {
                    index++
                    if (index > 6) {
                        index = 1
                    }
                }
            )
        }
    }
}

@Composable
fun ArtSpaceImage(
    index: Int
) {
    Image(
        painter = painterResource(id = image(index)),
        contentDescription = null,
        modifier = Modifier
            .padding(25.dp)
            .wrapContentSize()
    )
}

@Composable
fun ArtSpaceInfo(
    index: Int
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = stringResource(id = information(index)),
            fontSize = 30.sp,
        )
        Spacer(
            Modifier.height(10.dp)
        )
        Text(
            text = stringResource(id = author(index)),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun FunctionButton(
    prevClick: () -> Unit,
    nextClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = prevClick,
            modifier = Modifier
                .fillMaxWidth(0.4f)
        ) {
            Text(
                text = "Previous",
                color = Color.White,
            )
        }
        Button(
            onClick = nextClick,
            modifier = Modifier
                .fillMaxWidth(0.6f)
        ) {
            Text(
                text = "Next",
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFunctionApp() {
    ArtSpaceTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ArtSpaceActivity()
        }
    }
}

private fun image(index: Int): Int {
    return when (index) {
        1 -> R.drawable.red_vineyards_at_arles_1888_jpg_large
        2 -> R.drawable.the_starry_night_jpg_large
        3 -> R.drawable.still_life_vase_with_fifteen_sunflowers_1888_1_jpg_large
        4 -> R.drawable.antique_3840759_jpg_large
        5 -> R.drawable.mona_lisa__by_leonardo_da_vinci__from_c2rmf_retouched
        else -> R.drawable.the_last_supper_restored_da_vinci_32x16
    }
}

private fun information(index: Int): Int {
    return when (index) {
        1 -> R.string.red_vineyards
        2 -> R.string.the_starry_night
        3 -> R.string.still_life
        4 -> R.string.irises
        5 -> R.string.mona_lisa
        else -> R.string.the_last_super
    }
}

private fun author(index: Int): Int {
    return when (index) {
        1 -> R.string.red_vineyards_author
        2 -> R.string.the_starry_night_author
        3 -> R.string.still_life_author
        4 -> R.string.irises_author
        5 -> R.string.mona_lisa_author
        else -> R.string.the_last_super_author
    }
}
