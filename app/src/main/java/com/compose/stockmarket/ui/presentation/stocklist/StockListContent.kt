package com.compose.stockmarket.ui.presentation.stocklist

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.stockmarket.model.StockList
import com.compose.stockmarket.ui.presentation.common.CustomTextField

@Composable
fun StockListContent(
    state: StockListState,
    OnEvent: (StockListEvent) -> Unit,
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
                onValueChange = { OnEvent(StockListEvent.TextChangedEvent(it)) },
                labelText = "Search stock list",
                onSearchClick = { OnEvent(StockListEvent.GetStockFlowEvent(state.query)) },
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth()
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

@Preview(
    name = "night mode", uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "light mode", uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun StockListPreview() {

    var stockList = mutableListOf<StockList>()
    (1..10).map {
        stockList.add(
            StockList(
                currency = it.toString(),
                description = it.toString(),
                displaySymbol = it.toString(),
                figi = it.toString(),
                isin = it.toString(),
                mic = it.toString(),
                shareClassFIGI = it.toString(),
                symbol = "TWTR",
                symbol2 = it.toString(),
                type = it.toString()
            )
        )
    }
    StockListContent(state = StockListState(stockList = stockList.toList()), OnEvent = {})
}
