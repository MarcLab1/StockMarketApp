package com.compose.stockmarket.ui.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    onSearchClick:(String) -> Unit,
    modifier: Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = {Text(labelText) },
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            Icon(
                Icons.Default.Search, "search",
                modifier = Modifier.clickable {
                    onSearchClick(value)
                })
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            capitalization = KeyboardCapitalization.Characters
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                onSearchClick(value)
            }
        ),
        modifier = modifier

    )
}