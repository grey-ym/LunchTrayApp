package com.grey.ym.lunchtray.ui

import androidx.lifecycle.ViewModel
import com.grey.ym.lunchtray.model.MenuItem
import com.grey.ym.lunchtray.model.MenuItem.*
import com.grey.ym.lunchtray.model.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateEntree(entree: EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }

    fun updateSideDish(sideDish: SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(sideDish, previousSideDish)
    }

    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(accompaniment, previousAccompaniment)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentUiState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            // subtract previous item price in case an item of this category was already added.
            val itemTotalPrice = currentUiState.itemTotalPrice - previousItemPrice + newItem.price
            // recalculate tax
            val tax = itemTotalPrice * taxRate
            currentUiState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = if (newItem is EntreeItem) newItem else currentUiState.entree,
                sideDish = if (newItem is SideDishItem) newItem else currentUiState.sideDish,
                accompaniment = if (newItem is AccompanimentItem) newItem else currentUiState.accompaniment
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}