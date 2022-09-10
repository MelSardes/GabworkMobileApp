package com.sardes.thegabworkproject.data.models

data class SecteurDActivite(
    val id: Int,
    val nom: String,
    val description: String,
    val imageId: Int,
    val swiped: Boolean = false
)
