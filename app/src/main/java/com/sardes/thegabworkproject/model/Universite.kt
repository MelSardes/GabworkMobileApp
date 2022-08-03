package com.sardes.thegabworkproject.model

data class Universite(
    val id_universite: Int,
    val nom_universite: String,
    val id_ville: Int,
    val coordonnees_geographiques_universite: String?,
    val telephone_universite: String,
    val email_universite: String,
    val adress_universite: String,
    val site_web_universite: String?
)
