package com.compose.stockmarket.ui.presentation

import com.compose.stockmarket.model.Stock

data class MainState(
    val stock: Stock? = null,
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val query: String = ""
)
