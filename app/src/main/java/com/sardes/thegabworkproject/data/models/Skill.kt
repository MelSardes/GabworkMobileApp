package com.sardes.thegabworkproject.data.models

data class Skill(
    val title: String,
    val level: String
)

sealed class SkillLevel(
    val level: String,
) {

    object level1 : SkillLevel("DÉBUTANT")
    object level2 : SkillLevel("INTERMÉDIAIRE")
    object level3 : SkillLevel("EXPÉRIMENTÉ")
    object level4 : SkillLevel("AVANCÉ")
    object level5 : SkillLevel("EXPERT")
}

val skillLevels = listOf(
    "DÉBUTANT",
    "INTERMÉDIAIRE",
    "EXPÉRIMENTÉ",
    "AVANCÉ",
    "EXPERT",
)
