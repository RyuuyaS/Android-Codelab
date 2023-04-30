package com.example.mycity.ui.state

import com.example.mycity.data.DataSource
import com.example.mycity.model.Recommendation

data class AppState(
    val categoryIndex: Int = 0,
    val detailIndex: Int = 0,
) {
    val recommendedList: List<Recommendation> by lazy {
        listOfNotNull(DataSource.abc[categoryIndex])[0]
    }
}
