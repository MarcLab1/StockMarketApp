package com.compose.stockmarket.ui.presentation.stocklist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.stockmarket.ui.presentation.common.CustomTextField

@Composable
fun StockListContent(
    state: StockListState,
    viewModel: StockListViewModel = viewModel(),
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
                onValueChange = { viewModel.OnEvent(StockListEvent.TextChangedEvent(it)) },
                labelText = "Search stock list",
                onSearchClick = { viewModel.OnEvent(StockListEvent.GetStockFlowEvent(state.query)) },
                modifier = Modifier.padding(start = 10.dp, end = 10.dp).fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(5.dp))

            if (state.stockList != null) {
                LazyColumn()
                {
                    itemsIndexed(state.stockList)
                    { index, stockLists ->
                        StockListItem(stockList = stockLists)
                    }
                }
            }
        }
        if (state.errorMessage.isNotEmpty())
            Text(
                state.errorMessage, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            )

        if (state.isLoading)
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(10.dp)
            )
    }
}
