package com.example.affirmationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmationapp.data.Datasource
import com.example.affirmationapp.model.Affirmation
import com.example.affirmationapp.ui.theme.AffirmationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationList(affirmationList = Datasource().loadAffirmations())
                }
            }
        }
    }
}

@Composable
fun AffirmationApp() {
    val context = LocalContext.current
    AffirmationAppTheme {}
}

@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.padding(8.dp), elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceID),
                contentDescription = stringResource(id = affirmation.stringResourceID),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(
                Modifier.height(10.dp)
            )
            Text(
                text = stringResource(id = affirmation.stringResourceID),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.displayMedium,
            )
        }
    }
}

@Composable
fun AffirmationList(
    affirmationList: List<Affirmation>,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(affirmationList) { affirmation ->
            AffirmationCard(affirmation)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAffirmationCard() {
    AffirmationAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            AffirmationList(affirmationList = Datasource().loadAffirmations())
        }
    }
}




