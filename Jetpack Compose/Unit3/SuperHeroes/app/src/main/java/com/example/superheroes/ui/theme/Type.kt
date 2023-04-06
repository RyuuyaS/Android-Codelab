package com.example.superheroes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.superheroes.R

val Cabin = FontFamily(
    Font(R.font.cabin_regular, FontWeight.Normal),
    Font(R.font.cabin_bold, FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = Cabin,
        fontWeight = FontWeight.Normal,
    ),
    displayLarge = TextStyle(
        fontFamily = Cabin,
        fontSize = 30.sp,
        fontWeight = FontWeight.Normal,
    ),
    displayMedium = TextStyle(
        fontFamily = Cabin,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    displaySmall = TextStyle(
        fontFamily = Cabin,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
)