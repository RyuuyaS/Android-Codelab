package com.example.mycity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tab(
    image: Int,
    name: Int,
    onClick: (Int) -> Unit,
    index: Int = 0
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onClick(index)
                }
        ) {
            Image(
                painter = painterResource(id = image),
                contentDescription = stringResource(id = name),
                modifier = Modifier
                    .height(140.dp)
                    .padding(end = 21.dp)
                    .weight(1.5f),
                contentScale = ContentScale.FillBounds,
            )
            Text(
                text = stringResource(id = name),
                fontSize = 24.sp,
                lineHeight = 40.sp,
                modifier = Modifier.weight(2f)
            )
        }
    }
    Spacer(modifier = Modifier.padding(15.dp))
}