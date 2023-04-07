package com.example.dessertclicker.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import com.example.dessertclicker.model.ShopInfo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel() : ViewModel() {
    private val _uiDessertState = MutableStateFlow(Dessert())
    val uiDessertState: StateFlow<Dessert> = _uiDessertState.asStateFlow()

    private val _uiShopInfoState = MutableStateFlow(ShopInfo())
    val uiShopInfoState = _uiShopInfoState.asStateFlow()

    private val desserts = Datasource.dessertList
    private var buffer = 0

    init {
        resetSold()
    }

    fun resetSold() {
        _uiDessertState.value = Dessert(
            imageId = desserts.first().imageId,
            price = desserts.first().price,
            startProductionAmount = desserts.first().startProductionAmount,
        )
        _uiShopInfoState.value = ShopInfo(revenue = 0, dessertSold = 0)
    }

    fun updateRevenueAndNextDessert() {
        // update revenue and number of dessert sold
        Log.d("MainActivity", "${_uiShopInfoState.value.revenue}")
        _uiShopInfoState.update { currentState ->
            currentState.copy(
                revenue = _uiShopInfoState.value.revenue.plus(_uiDessertState.value.price),
                dessertSold = _uiShopInfoState.value.dessertSold.inc()
            )
        }
        Log.d("MainActivity", "${_uiShopInfoState.value.revenue}")

        // update index -> update image, price, production amount
        determineDessertToShow()
    }


    /**
     * Determine which dessert to show.
     */
    private fun determineDessertToShow() {
        for (i in 0.._uiDessertState.value.index) {
            buffer += desserts[i].startProductionAmount
        }
        if (_uiShopInfoState.value.dessertSold >= buffer) {
            val index = _uiDessertState.value.index.inc()
            _uiDessertState.update {
                it.copy(
                    index = index,
                    imageId = desserts[index].imageId,
                    price = desserts[index].price,
                    startProductionAmount = desserts[index].startProductionAmount
                )
            }
        }
    }
}
