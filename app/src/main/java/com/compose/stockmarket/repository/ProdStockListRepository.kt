package com.compose.stockmarket.repository

import com.compose.stockmarket.model.StockList
import com.compose.stockmarket.network.ApiService
import com.compose.stockmarket.util.Constants
import com.compose.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ProdStockListRepository(private val apiService: ApiService) : StockListRepository {

    override fun getStockListFlow(query: String): Flow<Resource<List<StockList>>> {
        return flow {
            try {
                val response = apiService.getAllStocks().filter {
                    it.symbol?.contains(query) ?: false
                }
                emit(Resource.Success<List<StockList>>(data = response.toList()))

            } catch (exception: Exception) {
                emit(Resource.Error<List<StockList>>(message = exception.localizedMessage ?: Constants.ERROR_MSG))
            }
        }
    }
}