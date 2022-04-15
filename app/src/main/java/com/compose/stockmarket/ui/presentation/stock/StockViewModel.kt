package com.compose.stockmarket.ui.presentation.stock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.stockmarket.model.addPrice
import com.compose.stockmarket.repository.StockRepository
import com.compose.stockmarket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockViewModel @Inject constructor(
    private val repository: StockRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<StockState> = MutableStateFlow(StockState())
    val state: StateFlow<StockState> = _state.asStateFlow()

    fun OnEvent(event : StockEvents)
    {
        when(event){
            is StockEvents.TextChangedEvent ->{
                onTextChanged(event.query)
            }
            is StockEvents.GetStockFlowEvent ->{
                getStock(event.query)
            }
        }
    }
    private fun onTextChanged(newText: String)
    {
        _state.value = _state.value.copy(query = newText)
    }

    private fun getStock(query: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(errorMessage = "", isLoading = true)

            val response = repository.getStock(symbol = query.uppercase())
            when (response) {
                is Resource.Success ->{
                    _state.value = _state.value.copy(errorMessage = "", isLoading = false, stock = response.data)
                }
                is Resource.Error ->{
                    _state.value = _state.value.copy(errorMessage = response.message.toString(), isLoading = false, stock = null)
                }
                is Resource.Loading -> {
                    _state.value = _state.value.copy(errorMessage = "", isLoading = true, stock = null)
                }
            }
        }
    }

    private fun getStockAsync(query: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(errorMessage = "", isLoading = true)

            val response1 = async { repository.getStockProfile(symbol = query.uppercase()) }
            val response2 = async { repository.getStockPrice(symbol = query.uppercase()) }

            if (response1.await() is Resource.Success && response2.await() is Resource.Success) {
                _state.value = _state.value.copy(errorMessage = "", isLoading = false)

                response1.await().data?.let { stockProfile ->
                    response2.await().data?.let { stockPrice ->
                        _state.value = _state.value.copy(stock = stockProfile.addPrice(stockPrice))
                    }
                }
            } else if (response1.await() is Resource.Error || response2.await() is Resource.Error) {
                _state.value = _state.value.copy(errorMessage = response1.await().message.toString(), isLoading = false, stock = null)
            }
        }
    }

    private fun getStockFlow(query: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(errorMessage = "", isLoading = true)

            repository.getStockFlow(symbol = query.uppercase()).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(errorMessage = "", isLoading = false, stock = response.data)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(errorMessage = response.message.toString(), isLoading = false, stock = null)
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(errorMessage = "", isLoading = true, stock = null)
                    }
                }
            }
        }
    }
}