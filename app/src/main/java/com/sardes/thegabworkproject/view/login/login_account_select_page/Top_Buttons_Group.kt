package com.sardes.thegabworkproject.view.login.login_account_select_page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.BlueFlag
import com.sardes.thegabworkproject.ui.theme.GrayPic
import com.sardes.thegabworkproject.ui.theme.YellowFlag

@Composable
fun Top_Buttons_Group(modifier: Modifier){
    var leftButtonBackgroundColor by remember { mutableStateOf(YellowFlag) }
    var rightButtonBackgroundColor by remember { mutableStateOf(BlueFlag) }

    var leftButtonTextColor by remember { mutableStateOf(BlueFlag)}
    var rightButtonTextColor by remember { mutableStateOf(YellowFlag)}

    var leftButtonShape by remember {
        mutableStateOf(RoundedCornerShape(0.dp, 0.dp, 0.dp, 30.dp))
    }
    var rightButtonShape by remember {
        mutableStateOf(RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp))
    }

//    Store wich button is selected
    var isLoginButton by remember { mutableStateOf(true) }
    var isRegisterButton by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().background(GrayPic),
    ){
        Button(
            onClick = {
                if (leftButtonBackgroundColor == BlueFlag) {
//                    Register button is no longer selected
                    isRegisterButton = false

                    leftButtonBackgroundColor = YellowFlag
                    leftButtonTextColor = BlueFlag
                    leftButtonShape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 30.dp)

                    rightButtonBackgroundColor = BlueFlag
                    rightButtonTextColor = YellowFlag
                    rightButtonShape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
                }

            },
            colors = ButtonDefaults.buttonColors(leftButtonBackgroundColor),
            shape = leftButtonShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ){
            Text("Se connecter", color = leftButtonTextColor)
        }

        Button(
            onClick = {

                if (rightButtonBackgroundColor == BlueFlag){
//                    Login button is no longer selected
                    isLoginButton = false

                    rightButtonBackgroundColor = YellowFlag
                    rightButtonTextColor = BlueFlag
                    rightButtonShape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 0.dp)

                    leftButtonBackgroundColor = BlueFlag
                    leftButtonTextColor = YellowFlag
                    leftButtonShape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 0.dp)
                }

            },
            colors = ButtonDefaults.buttonColors(rightButtonBackgroundColor),
            shape = rightButtonShape,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ){
            Text("S'inscrire", color = rightButtonTextColor)
        }
    }
}
