package com.compose.stockmarket.network

import com.compose.stockmarket.network.price.StockPriceResponse
import com.compose.stockmarket.network.stockprofile.StockProfileResponse
import com.compose.stockmarket.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("quote")
    suspend fun getStockPrice(
        @Query("symbol") symbol: String,
        @Query("token") token: String = Constants.API_KEY
    ) : StockPriceResponse

    @GET("stock/profile2")
    suspend fun getStockProfile(
        @Query("symbol") symbol : String,
        @Query("token") token: String = Constants.API_KEY
    ) : StockProfileResponse
}