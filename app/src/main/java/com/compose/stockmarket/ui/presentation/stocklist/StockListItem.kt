package com.compose.stockmarket.ui.presentation.stocklist

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.stockmarket.model.StockList

@Composable
fun StockListItem(stockList: StockList) {
    Card(elevation = 2.dp, modifier = Modifier.padding(3.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 5.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(.4f)
                        .padding(start = 5.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    stockList.displaySymbol?.let { Text(it, style = MaterialTheme.typography.h5) }
                    stockList.description?.let { Text(it, style = MaterialTheme.typography.body2) }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                )
                {
                    stockList.currency?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.body1,
                        )
                    }
                    stockList.type?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.body2,
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    name = "night mode", uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "light mode", uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
private fun StockListItemPreview() {
    StockListItem(
        stockList = StockList(
            currency = "USD",
            description = "AIR TRANSPORT SERVICES GROUP",
            displaySymbol = "ATSG",
            figi = "BBG000BLW5F7",
            isin = null,
            mic = "XNAS",
            shareClassFIGI = "BBG001S8KJF9",
            symbol = "ATSG",
            symbol2 = "",
            type = "Common Stock"
        )
    )


}