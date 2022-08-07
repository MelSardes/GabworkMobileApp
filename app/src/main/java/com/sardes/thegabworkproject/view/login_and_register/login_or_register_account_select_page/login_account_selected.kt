package com.sardes.thegabworkproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sardes.thegabworkproject.navigation.Screen
import com.sardes.thegabworkproject.ui.theme.*

@Composable
fun LoginAccountSelected(navController:NavHostController){

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
                contentAlignment = Alignment.Center
            ){
                Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        buildAnnotatedString {
                            append("Revenir sur  ")
                            withStyle(style = SpanStyle(color = GreenFlag)){
                                append("G")
                            }
                            withStyle(style = SpanStyle(color = YellowFlag)){
                                append("a")
                            }
                            withStyle(style = SpanStyle(color = BlueFlag)){
                                append("b")
                            }
                            append("work")
                        },
                        fontFamily = yanone,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 60.sp,
                        textAlign = TextAlign.Center,

                    )
                }
            }
        }

        // Compte Entreprise
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(GrayPic)

        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp))
                    .background(BlueFlag)
                    .clickable { /* TODO: ASSOCIER A LA PAGE DE CONNEXION POUR LES COMPTES ENTREPRISES   */ },
            ){
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxWidth(0.54f)
                        .fillMaxHeight()){
                        Text(
                            text = "Compte Entreprise",
                            fontFamily = yanone,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                       Text(
                           text = "Faites des recrutements \n" +
                                   "pour votre entreprise, \n" +
                                   "que vous cherchiez \n" +
                                   "des employers \n" +
                                   "ou des stagiaires",
                           fontFamily = yanone,
                           fontWeight = FontWeight.Light,
                           fontSize = 20.sp,
                           color = Color.White,
                           maxLines = 5,
                           textAlign = TextAlign.Center
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.tall_building),
                        contentDescription = "Building",
                        Modifier
                            .fillMaxSize(1f)
                            .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp)),
                    )
                }
            }
        }

        // COMPTE STANDARD
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(BlueFlag)

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(60.dp, 0.dp, 0.dp, 60.dp))
                    .background(GrayPic)
                    .clickable {
                        navController.navigate(route = Screen.StandardLogin.route)
                    },
            ){
                Row{
                    Image(
                        painter = painterResource(id = R.drawable.business_3d),
                        contentDescription = "Building",
                        Modifier
                            .fillMaxWidth(0.54f)
                            .zIndex(1f)
                            .scale(1f)
                    )
                    Column (modifier = Modifier.fillMaxSize()){
                        Text(
                            text = "Compte Standard",
                            fontFamily = yanone,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = BlueFlag
                        )
                        Text(
                            text = "Recherchez des \n" +
                                    "services autour de vous \n" +
                                    "ou créez un profil \n" +
                                    "chercheur d’emploi ou \n" +
                                    "étudiant pour les \n" +
                                    "demandes de stages",
                            fontFamily = yanone,
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp,
                            color = BlueFlag,
                            maxLines = 6
                        )
                    }
                }
            }
        }


        // COMPTE INDEPENDENT
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(GrayPic)
            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 60.dp, 0.dp, 0.dp))
                    .background(BlueFlag)
                    .clickable { /* TODO: ASSOCIER A LA PAGE DE CONNEXION POUR LES COMPTES INDEPENDANTS   */ },
            ){
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxWidth(0.54f)
                        .fillMaxHeight()){
                        Text(
                            text = "Compte Independant",
                            fontFamily = yanone,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                        Text(
                            text = "Proposez vos services \n" +
                                    "autour de vous et \n" +
                                    "gagnez en visibilité",
                            fontFamily = yanone,
                            fontWeight = FontWeight.Light,
                            fontSize = 20.sp,
                            color = Color.White,
                            maxLines = 3,
                            textAlign = TextAlign.Center
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.black_man_plumber),
                        contentDescription = "Building",
                        Modifier
                            .fillMaxHeight(1f)
                            .fillMaxWidth(1f)
                            .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp)),
                    )
                }
            }
        }
    }
}


//@Preview(showSystemUi = true, device = Devices.PIXEL, name = "PIXEL")
//@Preview(showSystemUi = true, device = Devices.PIXEL_3A_XL, name = "PIXEL 3A XL")
@Preview(showSystemUi = true, device = Devices.PIXEL_4_XL, name = "PIXEL 4 XL")
@Composable
fun Login_Account_Selected_Preview(){
    LoginAccountSelected(navController = rememberNavController())
}

