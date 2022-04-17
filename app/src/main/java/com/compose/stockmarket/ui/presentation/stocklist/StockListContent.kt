package com.compose.stockmarket.ui.presentation.stocklist

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.stockmarket.model.StockList
import com.compose.stockmarket.ui.presentation.common.CustomTextField
import com.compose.stockmarket.ui.theme.StockMarketTheme

@Composable
fun StockListContent(
    state: StockListState,
    onEvent: (StockListEvent) -> Unit,
    enabled: Boolean
) {

    Box(modifier = Modifier.fillMaxSize().background(androidx.compose.material3.MaterialTheme.colorScheme.primary))
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        )
        {
            CustomTextField(
                value = state.query,
                onValueChange = { onEvent(StockListEvent.TextChangedEvent(it)) },
                labelText = "Search stock list",
                onSearchClick = { onEvent(StockListEvent.GetStockFlowEvent(state.query)) },
                enabled = enabled,
                modifier = Modifier
                    .fillMaxWidth()
            )
            //Spacer(modifier = Modifier.padding(5.dp))

            if (state.stockList != null) {
                LazyColumn(modifier = Modifier.padding(top = 5.dp))
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
                    .padding(10.dp), color = MaterialTheme.colorScheme.error
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
    name = "night mode", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true
)
@Preview(
    name = "light mode", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true
)
@Composable
private fun StockListPreview() {

    var stockList = mutableListOf<StockList>()
    (1..20).map {
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
    StockMarketTheme {
        StockListContent(
            state = StockListState(stockList = stockList.toList()),
            onEvent = {},
            enabled = true
        )
    }
}
