package com.sardes.thegabworkproject.ui.screens.signup

import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.ui.theme.YellowFlag

enum class TabPage(val icon: ImageVector){
    Account(Icons.Default.AccountBox),
    Personals(Icons.Default.Person),
    PreviewInformations(Icons.Default.ThumbUp)
}


@Composable
fun TabScreen(
    selectIndex: Int, onSelect: (TabPage)->Unit
) {
    TabRow(selectedTabIndex = selectIndex, contentColor = Color.White) {
        TabPage.values().forEachIndexed{index, tabPage ->
            Tab(
                selected = index == selectIndex,
                onClick = {
                    onSelect(tabPage)
                }, text = { Text(text = tabPage.name) },
                icon = { Icon(
                    imageVector = tabPage.icon,
                    contentDescription = null
                )},
                unselectedContentColor = Color.Gray,
                selectedContentColor = YellowFlag
            )
        }
    }
}

@Preview(name = "TabScreen")
@Composable
private fun PreviewTabScreen() {
    TabScreen(0, {})
}