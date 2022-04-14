package com.compose.stockmarket.repository

import com.compose.stockmarket.model.Stock
import com.compose.stockmarket.model.StockPrice
import com.compose.stockmarket.model.StockProfile
import com.compose.stockmarket.model.addPrice
import com.compose.stockmarket.network.ApiService
import com.compose.stockmarket.network.price.toStockPrice
import com.compose.stockmarket.network.stockprofile.toStockProfile
import com.compose.stockmarket.util.Constants
import com.compose.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProdStockRepository constructor(
    private val apiService: ApiService
) : StockRepository {

    override suspend fun getStockProfile(symbol: String): Resource<StockProfile> {
        try {
            val response = apiService.getStockProfile(symbol = symbol)

            if (response.name.isNullOrEmpty())
                return Resource.Error<StockProfile>(message = Constants.NO_STOCK_SYMBOL)
            else
                return (Resource.Success<StockProfile>(data = response.toStockProfile()))
        } catch (exception: Exception) {
            return Resource.Error<StockProfile>(message = exception.localizedMessage.toString())
        }
    }

    override suspend fun getStockPrice(symbol: String): Resource<StockPrice> {
        try {
            val response = apiService.getStockPrice(symbol = symbol)
            return Resource.Success<StockPrice>(data = response.toStockPrice())
        } catch (exception: Exception) {
            return Resource.Error<StockPrice>(message = exception.localizedMessage.toString())
        }
    }

    override suspend fun getStock(symbol: String): Resource<Stock> {
        try {
            val response1 = apiService.getStockProfile(symbol = symbol)

            if (response1.name.isNullOrEmpty())
                return Resource.Error<Stock>(message = Constants.NO_STOCK_SYMBOL)
            else {
                val response2 = apiService.getStockPrice(symbol = symbol)
                return Resource.Success(
                    data = response1.toStockProfile().addPrice(response2.toStockPrice())
                )
            }
        } catch (exception: Exception) {
            return Resource.Error<Stock>(message = exception.localizedMessage.toString())
        }
    }

    override fun getStockFlow(symbol: String): Flow<Resource<Stock>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response1 = apiService.getStockProfile(symbol = symbol)

                if (response1.name.isNullOrEmpty())
                    emit(Resource.Error<Stock>(message = Constants.NO_STOCK_SYMBOL))
                else {
                    val response2 = apiService.getStockPrice(symbol = symbol)
                    emit(
                        Resource.Success(
                            data = response1.toStockProfile().addPrice(response2.toStockPrice())
                        )
                    )
                }
                return@flow
            } catch (exception: Exception) {
                emit(Resource.Error<Stock>(message = exception.localizedMessage.toString()))
            }
        }
    }
}
