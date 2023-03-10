package com.example.affirmationapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val stringResourceID: Int,
    @DrawableRes val imageResourceID: Int
)