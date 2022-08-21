package com.sardes.thegabworkproject.ui.screens.main.mainSeeker.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.BlueVariant
import com.sardes.thegabworkproject.ui.theme.YellowFlag

@Composable
fun SearchSeekerScreen(
    modifier: Modifier = Modifier,
) {
    SearchBar(onTextChange = {/* TODO */})
}



@Composable
fun SearchBar(onTextChange: (String) -> Unit){
    var text by rememberSaveable { mutableStateOf("") }

    Surface{
        Row{
            TextField(
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Black.copy(alpha = 0.2f),
                    focusedIndicatorColor = BlueVariant,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = YellowFlag,
                    textColor = BlueFlag
                ),
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    onTextChange(it)
                },
                placeholder = { R.string.look_for}
            )
        }
    }
}




@Preview(name = "SavesScreen")
@Composable
private fun PreviewSavesScreen() {
    SearchSeekerScreen()
}