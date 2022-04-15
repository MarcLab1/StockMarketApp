package com.compose.stockmarket.ui.presentation.stocklist

import com.compose.stockmarket.model.StockList

data class StockListState(
    val stockList: List<StockList>? = emptyList(),
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val query: String = ""
)
