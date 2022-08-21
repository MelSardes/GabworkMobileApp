package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.applications.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sardes.thegabworkproject.models.CompteEntreprise

@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Composable
fun PostCardComponent(Post: CompteEntreprise.PostVacant) {
    Card(
        modifier = Modifier.padding(10.dp).fillMaxWidth().height(160.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3)),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            Arrangement.SpaceAround
        ) {
            Column(modifier = Modifier.weight(0.7f), Arrangement.SpaceEvenly) {
                Text(
                    text = Post.postName,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = Post.typeDEmploi,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
                Text(
                    text = Post.adresse,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }

            Column(modifier = Modifier.weight(0.3f), Arrangement.SpaceEvenly) {
                Text(
                    text = if (Post.actif) "Actif" else "Inactif",
                    color = if (Post.actif) Color(0xFF0F730C) else Color(0xFFDB1E1E),
                    modifier = if (Post.actif) {
                        Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFEDF9F0))
                            .padding(5.dp)
                    } else {
                        Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFFEFEF))
                            .padding(5.dp)
                    }
                )
                Text(
                    text = "${Post.salaire} Fcfa",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

/*
@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Preview(showBackground = true)
@Composable
fun Test() {
    Card(
        Modifier.width(300.dp).height(150.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3)),

//        elevation = 0.dp
    ) {
        Row (
            modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally)
        ){
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "title", style = MaterialTheme.typography.h4, color = Color.White)
                Text(
                    text = "subtitle",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "header",
                    fontSize = 28.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.SpaceEvenly
            ){
                Text(text = "title", fontSize = 14.sp, color = Color.White)
                Text(
                    text = "subtitle",
                    fontSize = 16.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}*/
