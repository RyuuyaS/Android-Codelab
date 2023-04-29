package com.example.mycity.ui.state

import androidx.annotation.StringRes
import com.example.mycity.R

enum class Screen(@StringRes name: Int) {
    HomeScreen(R.string.app_name),
    CategoryScreen(R.string.category_screen),
    DetailScreen(R.string.detail_screen)
}