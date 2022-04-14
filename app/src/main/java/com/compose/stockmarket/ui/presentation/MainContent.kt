package com.compose.stockmarket.ui.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainContent(
    state: MainState,
    viewModel: MainViewModel = viewModel(),
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        )
        {
            TextField(
                value = state.query,
                onValueChange = {
                    viewModel.OnEvent(MainEvents.TextChangedEvent(it))
                },
                //onTextChanged(it) },
                trailingIcon = {
                    Icon(
                        Icons.Default.Search, "search",
                        modifier = Modifier.clickable {
                            //getStockFlow(state.query)
                            viewModel.OnEvent(MainEvents.GetStockFlowEvent(state.query))
                        })},
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Characters
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                //getStockFlow(state.query)
                viewModel.OnEvent(MainEvents.GetStockFlowEvent(state.query))
            }
        ),
        modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(10.dp))

        if (state.stock != null) {
            state.stock?.let { StockItem(it) }
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
