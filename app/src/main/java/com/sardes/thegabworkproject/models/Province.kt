package com.sardes.thegabworkproject.models

data class Province(
    val id_province: Int,
    val nom_province: String,
    val coordonnees_geographiques_province: String?
){
    data class Ville(
        val id_ville: Int,
        val id_province: Int,
        val nom_ville: String,
        val coordonnees_geographiques_ville: String?
    )
}