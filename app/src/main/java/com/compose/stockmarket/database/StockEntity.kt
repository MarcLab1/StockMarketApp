package com.compose.stockmarket.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.compose.stockmarket.model.Stock

@Entity(tableName = "stocks")
data class StockEntity(

    val country: String?,
    val currency: String?,
    val exchange: String?,
    val finnhubIndustry: String?,
    val ipo: String?,
    val logo: String?,
    val marketCapitalization: Double?,
    @PrimaryKey(autoGenerate = false)
    val name: String,

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

fun StockEntity.toStock() : Stock
{
    return Stock(
        country = country,
        currency = currency,
        exchange = exchange,
        finnhubIndustry = finnhubIndustry,
        ipo = ipo,
        logo = logo,
        marketCapitalization = marketCapitalization,
        name = name,
        phone = phone,
        shareOutstanding = shareOutstanding,
        ticker = ticker,
        weburl = weburl,
        currentPrice = currentPrice,
        change = change,
        percentChange = percentChange,
        highPrice = highPrice,
        lowPrice = lowPrice,
        openPrice = openPrice,
        previousClosePrice = previousClosePrice,
        time = time
    )
}