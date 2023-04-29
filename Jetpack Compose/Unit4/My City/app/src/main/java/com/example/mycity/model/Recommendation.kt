package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    val index: Int,
    @StringRes val name: Int,
    @DrawableRes val image: Int,
    @StringRes val description: Int,
)
