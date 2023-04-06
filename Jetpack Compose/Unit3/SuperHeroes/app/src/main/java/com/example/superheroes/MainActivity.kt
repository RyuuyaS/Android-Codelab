package com.example.superheroes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.Data.DataSource
import com.example.superheroes.model.SuperHero
import com.example.superheroes.ui.theme.SuperHeroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperHeroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SuperHeroApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroApp() {
    val list = DataSource().getList()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
                 SuperHeroAppBar()
        },
    ) {
        SuperHeroList(list = list, modifier = Modifier.padding(it))
    }

}

@Composable
fun SuperHeroList(
    list: List<SuperHero>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(list) { index, hero ->
            SuperHeroTab(
                hero = hero,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun SuperHeroTab(
    hero: SuperHero,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .sizeIn(minHeight = 72.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = hero.name),
                    style = MaterialTheme.typography.displaySmall,
                )
                Text(
                    text = stringResource(id = hero.detail),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = hero.image),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}

@Composable
fun SuperHeroAppBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Superheroes",
            style = MaterialTheme.typography.displayLarge,
        )
    }
}

@Preview
@Composable
fun PreviewSuperHeroTab() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        SuperHeroApp()
    }
}
