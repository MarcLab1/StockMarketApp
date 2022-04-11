package com.compose.stockmarket.model

data class StockProfile(
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


fun StockProfile.addPrice(stockPrice: StockPrice) : Stock {
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
        currentPrice = stockPrice.currentPrice,
        change = stockPrice.change,
        percentChange = stockPrice.percentChange,
        highPrice = stockPrice.highPrice,
        lowPrice = stockPrice.lowPrice,
        openPrice = stockPrice.openPrice,
        previousClosePrice = stockPrice.previousClosePrice,
        time = stockPrice.time
    )
}
