package com.sardes.thegabworkproject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.screens.login.LoginViewModel
import com.sardes.thegabworkproject.ui.theme.*


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun GetStartedScreen(
    loginViewModel: LoginViewModel?,
    onNavToMainPage:() -> Unit,
    onNavigateToLoginOrRegisterPage:() -> Unit
) {

    LaunchedEffect(key1 = loginViewModel?.hasUser){
        if (loginViewModel?.hasUser == true){
            onNavToMainPage.invoke()
        }
    }


    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.8f),
    ){
        Column (
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ){
            Get_Started_top_box()
            Get_Started_Text()
            Button(
                onClick = { onNavigateToLoginOrRegisterPage.invoke()},
                colors = buttonColors(backgroundColor = BlueFlag),
                shape = RoundedCornerShape(30.dp),
            ) {
                Text(
                    "Commencer",
                    color = YellowFlag,
                    textAlign = TextAlign.Center,
                    fontFamily = yanone,
                    fontWeight = FontWeight.Medium
                )
            }

        }
//                onClick = navController.navigate(route = Screen.LoginOrRegister.route))
    }
}

@Composable
fun Get_Started_top_box() {
    Box(
        modifier = Modifier
            .padding(5.dp, 10.dp, 5.dp, 0.dp)
            .clip(RoundedCornerShape(30.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .background(SmoothBrown),
        contentAlignment = Alignment.Center,
    ){
        Image(
            painter = painterResource(id = R.drawable.gabwork_on_start_illustration_medium),
            contentDescription = "Gabwork on start illustration"
        )
    }
}

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun Get_Started_Text(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            buildAnnotatedString {
                append("Devenez ")
                withStyle(style = SpanStyle(color = GreenFlag)){
                    append("G")
                }
                withStyle(style = SpanStyle(color = YellowFlag)){
                    append("a")
                }
                withStyle(style = SpanStyle(color = BlueFlag)){
                    append("b")
                }
                append("worker")
            },
            fontFamily = yanone,
            fontWeight = FontWeight.SemiBold,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
            )

        Text(text = "Un lieu pour les entreprises, \n" +
                "les chercheurs d’emploie, \n" +
                "les étudiants,\n" +
                "les travailleurs indépendants\n" +
                "et tout le monde ",
            fontFamily = yanone,
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(1f)
        )
    }
}


//@Preview
//@Preview(showSystemUi = true, showBackground = true, device = Devices.NEXUS_6)
//@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL)
////@Preview(showSystemUi = true, showBackground = true, device = Devices.PHONE)
//@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3A)
//@Composable
//fun PreviewGetStartedScreen(){
//    GetStartedScreen(
//        navController = rememberNavController()
//    )
//}
