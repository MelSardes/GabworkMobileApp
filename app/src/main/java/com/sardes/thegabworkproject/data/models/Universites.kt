package com.sardes.thegabworkproject.data.models

data class Universites(
    val id_universite: Int,
    val nom_universite: String,
    val domain: List<String>,
    val id_ville: Int,
    val coordonnees_geographiques_universite: String?,
    val telephone_universite: String,
    val email_universite: String,
    val adress_universite: String,
    val site_web_universite: String?
)
