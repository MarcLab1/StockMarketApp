package com.compose.stockmarket.ui.presentation.stock

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(viewModel: StockViewModel = hiltViewModel()) {

    val state by viewModel.state.collectAsState()
    StockContent(state = state)
}
