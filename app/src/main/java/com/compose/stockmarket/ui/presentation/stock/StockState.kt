package com.compose.stockmarket.ui.presentation.stock

import com.compose.stockmarket.model.Stock

data class StockState(
    val stock: Stock? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val query: String = ""
)
