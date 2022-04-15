package com.compose.stockmarket.ui.presentation.common

import android.content.res.Configuration
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomItem(
    topLeft: String,
    bottomLeft: String,
    topRight: String,
    bottomRight: String,
    iv: ImageVector?
) {
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
                    Text(topLeft, style = MaterialTheme.typography.h5)
                    Text(bottomLeft, style = MaterialTheme.typography.body2)

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                )
                {

                    Text(topRight, style = MaterialTheme.typography.body1)
                    Row()
                    {
                        if(iv!=null)
                            Image(imageVector = iv, contentDescription = "")
                        Text(bottomRight, style = MaterialTheme.typography.body2)

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
    CustomItem(
        topLeft = "tl",
        bottomLeft = "bl",
        topRight = "tr",
        bottomRight = "br",
        iv = Icons.Filled.KeyboardArrowUp
    )

}
