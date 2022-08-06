package com.sardes.thegabworkproject.view.login_and_register.login_or_register_account_select_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sardes.thegabworkproject.Login_Account_Selected
import com.sardes.thegabworkproject.Register_account_selected
import com.sardes.thegabworkproject.ui.theme.GrayPic


@Preview(showSystemUi = true)
@Composable
fun Tabs() {
    var tabIndex by remember { mutableStateOf(0) } // 1.
    val tabTitles = listOf("Connexion", "Inscription")
    Column { // 2.
        TabRow(selectedTabIndex = tabIndex) { // 3.
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = tabIndex == index, // 4.
                    modifier = Modifier.background(GrayPic),
                    onClick = { tabIndex = index },
                    text = { Text(text = title) }) // 5.
            }
        }
        when (tabIndex) { // 6.
            0 -> Login_Account_Selected()
            1 -> Register_account_selected()
        }
    }
}