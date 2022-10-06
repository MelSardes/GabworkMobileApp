package com.sardes.thegabworkproject.data.models

data class ActivityArea(
    val id: Int,
    val name: String,
    val description: String,
    val imageId: Int,
    val swiped: Boolean = false
)
