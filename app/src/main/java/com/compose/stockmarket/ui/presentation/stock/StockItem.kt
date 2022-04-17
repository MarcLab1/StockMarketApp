package com.compose.stockmarket.ui.presentation.stock

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.compose.stockmarket.model.Stock
import com.compose.stockmarket.R
import com.compose.stockmarket.ui.presentation.common.CustomItem
import com.compose.stockmarket.ui.theme.StockMarketTheme

@Composable
fun StockItem(stock: Stock) {
    Card(elevation = 4.dp) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp)
                .background(color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(.2f)
                        .padding(start = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    AsyncImage(
                        model = stock.logo,
                        placeholder = painterResource(id = R.drawable.blank),
                        contentDescription = "image",
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .padding(start = 5.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    stock.name?.let { Text(it, style = MaterialTheme.typography.body2) }
                    stock.ticker?.let { Text(it, style = MaterialTheme.typography.h5) }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 10.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                )
                {
                    stock.currentPrice?.let {
                        Text(
                            "$" +
                                    "%.2f".format(it),
                            style = MaterialTheme.typography.h5
                        )
                    }
                    Row()
                    {
                        stock.percentChange?.let {
                            if (it > 0)
                                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "up")
                            else
                                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "down")

                            Text(
                                showPercentage(it),
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }

                }
            }
        }
    }
}

private fun showPercentage(percent: Double): String {
    return "%.2f".format(percent) + "%"
}

@Preview(
    name = "night mode", uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "light mode", uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun StockItemPreview() {
    StockMarketTheme {
        StockItem(
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
        )
    }
}
