package com.compose.stockmarket.ui.presentation.stocklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.stockmarket.repository.StockListRepository
import com.compose.stockmarket.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StockListViewModel @Inject constructor(
    private val repository: StockListRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<StockListState> = MutableStateFlow(StockListState())
    val state: StateFlow<StockListState> = _state.asStateFlow()

    init {
        getStockListFlow(state.value.query)
    }

    fun OnEvent(event : StockListEvent)
    {
        when(event){
            is StockListEvent.TextChangedEvent ->{
                onTextChanged(event.query)
            }
            is StockListEvent.GetStockFlowEvent ->{
                getStockListFlow(event.query)
            }
        }
    }
    private fun onTextChanged(newText: String)
    {
        _state.value = _state.value.copy(query = newText)
    }


    private fun getStockListFlow(query: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(errorMessage = "", isLoading = true)

            repository.getStockListFlow(query = query.uppercase()).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(errorMessage = "", isLoading = false, stockList = response.data)
                    }
                    is Resource.Error -> {
                        _state.value = _state.value.copy(errorMessage = response.message.toString(), isLoading = false, stockList = null)
                    }
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(errorMessage = "", isLoading = true, stockList = null)
                    }
                }
            }
        }
    }
}