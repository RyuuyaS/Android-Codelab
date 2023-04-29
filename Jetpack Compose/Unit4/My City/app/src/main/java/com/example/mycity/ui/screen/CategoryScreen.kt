package com.example.mycity.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycity.model.Recommendation

@Composable
fun CategoryScreen(
    onClick: (Int) -> Unit,
    recommendedList: List<Recommendation>
) {
    LazyColumn(
        Modifier.padding(15.dp)
    ) {
        items(items = recommendedList) {
            Tab(name = it.name, image = it.image, onClick = onClick, index = it.index)
        }
    }
}