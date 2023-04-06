package com.example.grid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.grid.Data.DataSource
import com.example.grid.Model.Topic
import com.example.grid.ui.theme.GridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }){

        }
        items(DataSource.topics) { topic ->
            TopicGridItem(topic)
        }
    }
}

@Composable
fun TopicGridItem(
    topic: Topic
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
        ) {
            Box {
                Image(
                    painter = painterResource(id = topic.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
            ) {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(
                            top = 16.dp,
                            bottom = 8.dp,
                            start = 16.dp,
                            end = 16.dp,
                        ),
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 8.dp)
                    )
                    Text(
                        text = topic.number.toString(),
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopicGridItem() {
    GridTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TopicGridItem(Topic(R.string.photography, 321, R.drawable.photography))
        }
    }
}
