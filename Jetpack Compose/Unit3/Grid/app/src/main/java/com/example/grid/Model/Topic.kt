package com.example.grid.Model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.painter.Painter

data class Topic(
    @StringRes val name: Int,
    val number: Int,
    @DrawableRes val icon: Int,
)
