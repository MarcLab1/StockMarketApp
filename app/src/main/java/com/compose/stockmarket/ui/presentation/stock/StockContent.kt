package com.compose.stockmarket.ui.presentation.stock

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.stockmarket.ui.presentation.common.CustomTextField

@Composable
fun StockContent(
    state: StockState,
    viewModel: StockViewModel = viewModel(),
) {

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        )
        {
            CustomTextField(
                value = state.query,
                onValueChange = { viewModel.OnEvent(StockEvents.TextChangedEvent(it)) },
                labelText = "Search Stock",
                onSearchClick = { viewModel.OnEvent(StockEvents.GetStockFlowEvent(state.query)) },
                modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth())

            Spacer(modifier = Modifier.padding(5.dp))

            state.stock?.let { StockItem(it) }
        }
        if (state.errorMessage.isNotEmpty())
            Text(
                state.errorMessage, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp), color = MaterialTheme.colors.error
            )

        if (state.isLoading)
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            )
    }

}
