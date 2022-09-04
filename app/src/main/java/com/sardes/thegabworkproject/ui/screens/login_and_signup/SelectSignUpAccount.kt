package com.sardes.thegabworkproject.ui.screens.login_and_signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.R
import com.sardes.thegabworkproject.ui.theme.*

@Composable
fun SelectSignUpAccount(
    onNavToEntrepriseSignUpAccount: () -> Unit = {},
    onNavToIndependantSignUpAccount: () -> Unit = {},
    onNavToStandardSignUpAccount: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(GrayPic)
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 60.dp, 0.dp))
                    .background(BlueFlag),
            ){
                Column(verticalArrangement = Arrangement.Top) {
                    Text(
                        buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.White)){
                                append("Rejoindre la communauté ")
                            }
                            withStyle(style = SpanStyle(color = GreenFlag)){
                                append("G")
                            }
                            withStyle(style = SpanStyle(color = YellowFlag)){
                                append("a")
                            }
                            withStyle(style = SpanStyle(color = BlueVariant)){
                                append("b")
                            }
                            withStyle(style = SpanStyle(color = Color.White)){
                                append("work")
                            }                        },
                        fontFamily = yanone,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center,
                    )

                    Text(
                        text = "Sélectionnez le type de compte que vous souhaitez créer",
                        fontFamily = yanone,
                        fontWeight = FontWeight.Medium,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }

        // Compte Entreprise
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(BlueFlag)
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(60.dp, 0.dp, 60.dp, 0.dp))
                    .background(GrayPic)
                    .clickable { onNavToEntrepriseSignUpAccount.invoke() }
            ){
                Row(modifier = Modifier
                    .align(Center)
                    .padding(5.dp)){
/*
                    Image(
                        painter = painterResource(id = R.drawable.tall_building),
                        contentDescription = "Building",
                        Modifier
                            .fillMaxHeight(1f)
                            .fillMaxWidth(0.44f)
                            .clip(RoundedCornerShape(60.dp, 0.dp, 0.dp, 0.dp))
                    )
*/

                    Spacer(modifier = Modifier.width(10.dp))

                    Column (modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight()){
                        Text(
                            text = "Compte Entreprise",
                            fontFamily = yanone,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = BlueFlag
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
                            color = BlueFlag,
                            maxLines = 5
                        )
                    }
                }
            }
        }

        // COMPTE STANDARD
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(GrayPic)
                .weight(1f),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(60.dp, 0.dp, 60.dp, 0.dp))
                    .background(BlueFlag)
                    .padding(10.dp)
                    .clickable { onNavToStandardSignUpAccount.invoke() }
            ){
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ){
                    Column (modifier = Modifier
                        .fillMaxWidth(0.54f)
                        .fillMaxHeight()){
                        Text(
                            text = "Compte Standard",
                            fontFamily = yanone,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
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
                            color = Color.White,
                            maxLines = 5,
                            textAlign = TextAlign.Center
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.business_3d),
                        contentDescription = "Building",
                        Modifier
                            .fillMaxSize(1f)
                            .clip(RoundedCornerShape(0.dp, 60.dp, 60.dp, 0.dp)),
                    )
                }
            }
        }

        // COMPTE INDEPENDENT
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(BlueFlag)
                .weight(1f),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(60.dp, 0.dp, 0.dp, 0.dp))
                    .background(GrayPic)
                    .clickable { onNavToIndependantSignUpAccount.invoke()}
            ) {
                Row{
                    Image(
                        painter = painterResource(id = R.drawable.black_man_plumber),
                        contentDescription = "Building",
                        Modifier
                            .fillMaxHeight(1f)
                            .fillMaxWidth(0.44f)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    
                    Column (modifier = Modifier
                        .fillMaxSize(1f)
                        .align(CenterVertically)){
                        Text(
                            text = "Compte Independant",
                            fontFamily = yanone,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center,
                            color = BlueFlag
                        )
                        Text(
                            text = "Proposez vos services \n" +
                                    "autour de vous et \n" +
                                    "gagnez en visibilité",
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
    }
}

@Preview(name = "Register_account_selected")
@Composable
private fun PreviewRegister_account_selected() {
    SelectSignUpAccount({}, {}, {})
}