package com.example.mycity.ui.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.sp
import com.example.mycity.R

@Composable
fun DetailScreen(
    @StringRes name: Int,
    @DrawableRes image: Int,
    @StringRes description: Int,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = stringResource(id = name),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(id = name),
                fontSize = 36.sp,
                lineHeight = 40.sp
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Text(
                text = stringResource(id = description),
                modifier = Modifier.weight(1f),
                fontSize = 20.sp,
                lineHeight = 40.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        DetailScreen(
            name = R.string.app_name,
            image = R.drawable.vingroup,
            description = R.string.description
        )
    }
}