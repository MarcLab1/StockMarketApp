package com.compose.stockmarket.repository

import com.compose.stockmarket.model.StockList
import com.compose.stockmarket.model.Stock
import com.compose.stockmarket.model.StockPrice
import com.compose.stockmarket.model.StockProfile
import com.compose.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getStockProfile(symbol: String) : Resource<StockProfile>
    suspend fun getStockPrice(symbol: String) : Resource<StockPrice>
    suspend fun getStock(symbol: String) : Resource<Stock>
    fun getStockFlow(symbol: String) : Flow<Resource<Stock>>
    fun getStockCache(symbol : String) : Stock


}