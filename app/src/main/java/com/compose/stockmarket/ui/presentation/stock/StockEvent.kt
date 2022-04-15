package com.compose.stockmarket.ui.presentation.stock

sealed class StockEvent{
    data class TextChangedEvent(val query: String) : StockEvent()
    data class GetStockFlowEvent(val query: String) : StockEvent()
}
