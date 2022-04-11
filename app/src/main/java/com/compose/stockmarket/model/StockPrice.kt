package com.compose.stockmarket.model

data class StockPrice (
    val currentPrice: Double?,
    val change: Double?,
    val percentChange: Double?,
    val highPrice: Double?,
    val lowPrice: Double?,
    val openPrice: Double?,
    val previousClosePrice: Double?,
    val time: Long?
    )