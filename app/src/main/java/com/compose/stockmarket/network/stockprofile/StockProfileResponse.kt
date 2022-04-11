package com.compose.stockmarket.network.stockprofile

import com.compose.stockmarket.model.StockProfile

data class StockProfileResponse(
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
    val weburl: String?
)

fun StockProfileResponse.toStockProfile(): StockProfile {
    return StockProfile(
        country = country,
        currency = currency,
        exchange = exchange,
        finnhubIndustry = finnhubIndustry,
        ipo = ipo,
        logo = logo,
        marketCapitalization = marketCapitalization,
        name = name ?: "",
        phone = phone,
        shareOutstanding = shareOutstanding,
        ticker = ticker,
        weburl = weburl
    )
}