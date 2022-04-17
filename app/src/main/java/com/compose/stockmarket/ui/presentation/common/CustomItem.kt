package com.compose.stockmarket.ui.presentation.common

import android.content.res.Configuration
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.stockmarket.ui.theme.StockMarketTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomItem(
    topLeft: String,
    bottomLeft: String,
    topRight: String,
    bottomRight: String,
    iv: ImageVector?
) {
    Card( modifier = Modifier.padding(3.dp)) {
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
                    Text(topLeft, style = androidx.compose.material3.MaterialTheme.typography.bodySmall)
                    Text(bottomLeft, style = androidx.compose.material3.MaterialTheme.typography.bodySmall)

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                )
                {

                    Text(topRight, style = androidx.compose.material3.MaterialTheme.typography.bodySmall)
                    Row()
                    {
                        if (iv != null)
                            Image(imageVector = iv, contentDescription = "")
                        Text(bottomRight, style = androidx.compose.material3.MaterialTheme.typography.bodySmall)

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
private fun CustomItemPreview() {
    StockMarketTheme {
        CustomItem(
            topLeft = "tl",
            bottomLeft = "bl",
            topRight = "tr",
            bottomRight = "br",
            iv = Icons.Filled.KeyboardArrowUp
        )
    }
}
