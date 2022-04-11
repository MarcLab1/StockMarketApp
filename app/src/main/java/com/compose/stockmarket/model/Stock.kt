package com.compose.stockmarket.model

data class Stock(
    val country: String?,
    val currency: String?,
    val exchange: String?,
    val finnhubIndustry: String?,
    val ipo: String?,
    val logo: String?,
    val marketCapitalization: Double?,
    val name: String?,
    val phone: String?,
    val shareOutstanding: Double?,
    val ticker: String?,
    val weburl: String?,
    val currentPrice: Double?,
    val change: Double?,
    val percentChange: Double?,
    val highPrice: Double?,
    val lowPrice: Double?,
    val openPrice: Double?,
    val previousClosePrice: Double?,
    val time: Long?
)

