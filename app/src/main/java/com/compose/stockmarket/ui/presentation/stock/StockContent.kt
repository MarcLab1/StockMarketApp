package com.compose.stockmarket.ui.presentation.stock

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.stockmarket.model.Stock
import com.compose.stockmarket.ui.presentation.common.CustomTextField
import com.compose.stockmarket.ui.theme.StockMarketTheme

@Composable
fun StockContent(
    state: StockState,
    onEvent: (StockEvent) -> Unit,
    enabled: Boolean
) {

    Box(modifier = Modifier.fillMaxSize().background(androidx.compose.material3.MaterialTheme.colorScheme.primary))
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)

        )
        {
            CustomTextField(
                value = state.query,
                onValueChange = { onEvent(StockEvent.TextChangedEvent(it)) },
                labelText = "Search stock",
                onSearchClick = { onEvent(StockEvent.GetStockFlowEvent(state.query)) },
                enabled = enabled,
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(5.dp))

            state.stock?.let { StockItem(it) }
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
                    .padding(10.dp),
                    color = MaterialTheme.colorScheme.inversePrimary)
    }

}

@Preview(
    name = "night mode", uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "light mode", uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun StockContentPreview() {
    StockMarketTheme {
        StockContent(state = StockState(
            stock = Stock(
                country = "US",
                currency = "USD",
                exchange = "NEW YORK STOCK EXCHANGE, INC.",
                finnhubIndustry = "Media",
                ipo = "2013-11-07",
                logo = "https://static.finnhub.io/logo/aa4a7988-80ce-11ea-b927-00000000092a.png",
                marketCapitalization = 37013.64,
                name = "Twitter Inc",
                phone = "14152229670.0",
                shareOutstanding = 800.64,
                ticker = "TWTR",
                weburl = "https://twitter.com/",
                currentPrice = 46.5899,
                change = 0.3599,
                percentChange = 0.7785,
                highPrice = 47.89,
                lowPrice = 44.72,
                openPrice = 45.75,
                previousClosePrice = 46.23,
                time = 1649694198
            )
        ), onEvent = {}, enabled = true
        )
    }

}
