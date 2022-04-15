package com.compose.stockmarket.repository

import com.compose.stockmarket.model.StockList
import com.compose.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockListRepository {


    fun getStockListFlow(query: String) : Flow<Resource<List<StockList>>>
}