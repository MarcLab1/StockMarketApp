package com.compose.stockmarket.ui.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel()) {

    val state by viewModel.state.collectAsState()

    MainContent(
        state = state,
        onTextChanged = viewModel::onTextChanged,
        getStockFlow = { viewModel.getStockFlow(it)})


}
