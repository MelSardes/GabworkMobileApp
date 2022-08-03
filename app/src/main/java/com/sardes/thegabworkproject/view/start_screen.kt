package com.sardes.thegabworkproject.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.theme.SmoothBrown
import com.sardes.thegabworkproject.ui.theme.YellowFlag

@Composable
fun Start_Screen() {
    Column {
        Box(
            modifier = Modifier
                .padding(5.dp, 10.dp, 5.dp, 0.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(SmoothBrown)
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        ){
/*            Image(
                painter = painterResource(id = R.drawable.gabwork_on_start_illustration),
                contentDescription = "Gabwork on start illustration"
            )*/


        }
    }
}


@Composable
fun Entry_Text(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Devenez Gabworker",
            color = YellowFlag,
            style = MaterialTheme.typography.h1
            )
        Text(text = "Un lieu pour les entreprises, \n" +
                "les chercheurs d’emploie, \n" +
                "les étudiants,\n" +
                "les travailleurs indépendants\n" +
                "et tout le monde ")
    }
}

@Preview
@Composable
fun Preview_Text(){
    Entry_Text()
}

/*
@Preview(showSystemUi = true, showBackground = true, device = Devices.NEXUS_6)
@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL)
@Preview(showSystemUi = true, showBackground = true, device = Devices.PHONE)
@Preview(showSystemUi = true, showBackground = true, device = Devices.PIXEL_3A)
@Composable
fun Preview_screen(){
    Start_Screen()
}*/
