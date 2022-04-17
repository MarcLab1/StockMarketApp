package com.compose.stockmarket.ui.presentation.stocklist

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StockListScreen(viewModel: StockListViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()
    StockListContent(
        state = state, onEvent = { viewModel.onEvent(it) },
        enabled = !state.isLoading
    )
}
