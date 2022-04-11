package com.compose.stockmarket.network.price

import com.compose.stockmarket.model.StockPrice

data class StockPriceResponse(
    val c: Double?,
    val d: Double?,
    val dp: Double?,
    val h: Double?,
    val l: Double?,
    val o: Double?,
    val pc: Double?,
    val t: Long?
)

fun StockPriceResponse.toStockPrice() : StockPrice
{
    return StockPrice(
        currentPrice = c,
        change = d,
        percentChange = dp,
        highPrice = h,
        lowPrice = l,
        openPrice = o,
        previousClosePrice = pc,
        time = t
    )
}