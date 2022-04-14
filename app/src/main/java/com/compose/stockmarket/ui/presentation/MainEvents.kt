package com.compose.stockmarket.ui.presentation

sealed class MainEvents{
    data class TextChangedEvent(val query: String) : MainEvents()
    data class GetStockFlowEvent(val query: String) : MainEvents()
}
