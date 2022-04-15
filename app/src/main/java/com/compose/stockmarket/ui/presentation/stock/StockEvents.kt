package com.compose.stockmarket.ui.presentation.stock

sealed class StockEvents{
    data class TextChangedEvent(val query: String) : StockEvents()
    data class GetStockFlowEvent(val query: String) : StockEvents()
}
