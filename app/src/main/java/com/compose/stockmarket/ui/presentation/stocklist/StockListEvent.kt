package com.compose.stockmarket.ui.presentation.stocklist

sealed class StockListEvent{
    data class TextChangedEvent(val query: String) : StockListEvent()
    data class GetStockFlowEvent(val query: String) : StockListEvent()
}
