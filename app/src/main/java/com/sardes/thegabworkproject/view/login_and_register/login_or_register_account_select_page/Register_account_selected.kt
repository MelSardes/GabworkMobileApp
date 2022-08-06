package com.sardes.thegabworkproject.view.login_and_register.login_or_register_account_select_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.GrayPic

@Composable
fun Register_account_selected(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(GrayPic),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 0.dp, 60.dp))
                    .background(BlueFlag),
            ){
                Column(verticalArrangement = Arrangement.Top) {
                    androidx.compose.material3.Text("Rejoindre la communauté Gabwork")
                }
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(BlueFlag),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp))
                    .background(GrayPic),
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(GrayPic),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(60.dp, 0.dp, 0.dp, 60.dp))
                    .background(BlueFlag),
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(BlueFlag),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 60.dp, 0.dp, 0.dp))
                    .background(GrayPic),
            )
        }
    }
}

@Preview(name = "Register_account_selected")
@Composable
private fun PreviewRegister_account_selected() {
    Register_account_selected()
}