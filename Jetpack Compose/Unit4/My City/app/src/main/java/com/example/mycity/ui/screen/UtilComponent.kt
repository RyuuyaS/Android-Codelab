package com.example.mycity.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                .size(130.dp)
                .padding(end = 20.dp)
        )
        Text(
            text = stringResource(id = name),
            fontSize = 24.sp,
            lineHeight = 40.sp
        )
    }
    Divider(color = Color.Black, thickness = 1.dp)
}