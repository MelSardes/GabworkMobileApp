package com.sardes.thegabworkproject.view.login_and_register.login_or_register_account_select_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.GrayPic
import com.sardes.thegabworkproject.ui.theme.yanone

@Preview(showSystemUi = true)
@Composable
fun Login_Account_Selected(){

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(BlueFlag),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 0.dp, 60.dp))
                    .background(GrayPic),
            ){
                Column(verticalArrangement = Arrangement.Top) {
                    Text(
                        "Revenir sur Gabwork",
                        fontFamily = yanone,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 50.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }
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
                    .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp))
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
                    .clip(RoundedCornerShape(60.dp, 0.dp, 0.dp, 60.dp))
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
                    .clip(RoundedCornerShape(0.dp, 60.dp, 0.dp, 0.dp))
                    .background(BlueFlag),
            )
        }
    }
}

