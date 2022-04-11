package com.compose.stockmarket.ui.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.stockmarket.model.Stock
import com.compose.stockmarket.model.addPrice
import com.compose.stockmarket.repository.StockRepository
import com.compose.stockmarket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: StockRepository,
) : ViewModel() {

    var stock: MutableState<Stock?> = mutableStateOf(null)
    var errorMessage: MutableState<String> = mutableStateOf("")
    var isLoading: MutableState<Boolean> = mutableStateOf(false)

    fun getStockAsync(query: String) {
        viewModelScope.launch {
            errorMessage.value = ""
            isLoading.value = true

            val response1 = async { repository.getStockProfile(symbol = query.uppercase()) }
            val response2 = async { repository.getStockPrice(symbol = query.uppercase()) }

            if (response1.await() is Resource.Success && response2.await() is Resource.Success) {
                errorMessage.value = ""
                isLoading.value = false
                response1.await().data?.let { stockProfile ->
                    response2.await().data?.let { stockPrice ->
                        stock.value = stockProfile.addPrice(stockPrice)
                    }
                }
            } else if (response1.await() is Resource.Error) {
                errorMessage.value = response1.await().message.toString()
                isLoading.value = false
                stock.value = null
            }
        }
    }

    fun getStock(query: String) {
        viewModelScope.launch {
            errorMessage.value = ""
            isLoading.value = true

            val response = repository.getStock(symbol = query.uppercase())
            when (response) {
                is Resource.Success ->{
                    errorMessage.value = ""
                    isLoading.value = false
                    stock.value = response.data
                }
                is Resource.Error ->{
                    errorMessage.value = response.message.toString()
                    isLoading.value = false
                    stock.value = null
                }
                is Resource.Loading ->
                {
                    errorMessage.value = ""
                    isLoading.value = true
                    stock.value = null
                }
            }
        }
    }

    fun getStockFlow(query: String) {
        viewModelScope.launch {
            errorMessage.value = ""
            isLoading.value = true

            repository.getStockFlow(symbol = query.uppercase()).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        errorMessage.value = ""
                        isLoading.value = false
                        stock.value = response.data
                    }
                    is Resource.Error -> {
                        errorMessage.value = response.message.toString()
                        isLoading.value = false
                        stock.value = null
                    }
                    is Resource.Loading -> {
                        errorMessage.value = ""
                        isLoading.value = true
                        stock.value = null
                    }
                }
            }
        }
    }
}