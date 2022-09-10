package com.sardes.thegabworkproject.ui.composition

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import kiwi.orbit.compose.ui.controls.TextField
import kiwi.orbit.compose.ui.controls.Text as OrbitCompose

@Composable
fun TextFieldAuthItem(
    focusRequester: FocusRequester = FocusRequester.Default,
    label: String,
    info: String,
    value: String,
    valueChange: (value: String) -> Unit,
) {
    TextField(
        value = value,
        onValueChange = { valueChange(it) },
        label = { OrbitCompose(label) },
        info = { OrbitCompose(info) },
        singleLine = false,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
    )
}

@Preview(name = "TextFieldItem")
@Composable
private fun PreviewTextFieldItem() {
    TextFieldAuthItem(
        value = "valeur",
        label = "Un champ standard",
        info = "Informations sur le champ"
    ) {}
}