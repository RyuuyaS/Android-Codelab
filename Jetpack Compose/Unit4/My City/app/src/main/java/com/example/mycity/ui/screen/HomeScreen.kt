package com.example.mycity.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycity.data.DataSource
import com.example.mycity.model.Category

@Composable
fun HomeScreen(
    categoryList: List<Category>,
    onClick: (Int) -> Unit,
) {
    LazyColumn(
        Modifier.padding(15.dp)
    ) {
        items(items = categoryList) {
            Tab(name = it.name, image = it.image, onClick = onClick, index = it.index)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(DataSource.categoryList) {}
}