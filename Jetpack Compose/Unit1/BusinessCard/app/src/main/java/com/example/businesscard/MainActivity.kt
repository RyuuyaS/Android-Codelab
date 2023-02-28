package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(156, 184, 231),
                ) {
                    FinalApp()
                }
            }
        }
    }
}

@Composable
fun Introduction() {
    val image = painterResource(id = R.drawable.poro)
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(painter = image, contentDescription = null)
        Text(
            text = "Thinh Poro",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
        )
        Text(
            text = "Android Developer Intern",
            color = Color.White,
            fontSize = 24.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIntroduction() {
    BusinessCardTheme {
        Surface(
            color = Color(156, 184, 231),
        ) {
            Introduction()
        }
    }
}

@Composable
fun Information(icon: Painter, info: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(bottom = 10.dp)
    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .padding(end = 10.dp)
        )
        Text(
            text = info,
            color = Color.White,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun BusinessCardInfo() {
    val phoneIcon = painterResource(id = R.drawable.baseline_contact_phone_24)
    val mailIcon = painterResource(id = R.drawable.baseline_contact_mail_24)
    val linkIcon = painterResource(id = R.drawable.baseline_link_24)
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,

        modifier = Modifier
            .padding(bottom = 40.dp, start = 65.dp)
            .fillMaxSize()
    ) {
        Information(icon = phoneIcon, info = "0523593830")
        Information(icon = linkIcon, info = "poro1008")
        Information(icon = mailIcon, info = "nguyenphungthinh03@gmail.com")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBusinessCardInfo() {
    Surface(
        color = Color(156, 184, 231)
    ) {
        BusinessCardInfo()
    }
}

@Composable
fun FinalApp() {
    Introduction()
    BusinessCardInfo()
}

@Preview(showBackground = true)
@Composable
fun PreviewFinalApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(156, 184, 231),
    ) {
        FinalApp()
    }
}