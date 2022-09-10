package com.sardes.thegabworkproject.data

import androidx.annotation.DrawableRes
import com.sardes.thegabworkproject.R



val universities = listOf(
    "UOB - Université Omar Bongo",
    "USTM - Université des Sciences et Techniques de Masuku",
    "INPTIC - Institut National de la Poste, des Technologies de l’Information et de la Communication",

)


sealed class University(
    val sigle: Int,
    val nom: Int,
    val mail: Int,
    val adresse: Int,
    val web: Int,
    val domaines: List<String>,
    val ville: Int,
    val telephone: List<String>,
    val description: Int,
    @DrawableRes logo: Int,
) {
    object UOB : University(
        nom = R.string.Nom_UOB,
        sigle = R.string.Sigle_UOB,
        mail = R.string.Mail_UOB,
        adresse = R.string.Adrese_UOB,
        telephone = listOf("+241 (0)11 730 000"),
        web = R.string.Web_UOB,
        ville = R.string.lbv,
        description = R.string.Description_UOB,
        domaines = domainesUOB,
        logo = R.drawable.logo_uob
    )

    object INPTIC : University(
        nom = R.string.nom_INPTIC,
        sigle = R.string.sigle_INPTIC,
        mail = R.string.mail_INPTIC,
        adresse = R.string.adresse_INPTIC,
        web = R.string.web_INPTIC,
        ville = R.string.lbv,
        description = R.string.description_INPTIC,
        domaines = domainesINPTIC,
        telephone = listOf(
            "+241 11 73 81 31",
            "+241 11 73 23 43",
            "+241 62 13 15 49",
            "+241 76 44 00 58",
        ),
        logo = R.drawable.logo_inptic,
    )

    object USTM : University(
        nom = R.string.nom_USTM,
        sigle = R.string.sigle_USTM,
        mail = R.string.mail_USTM,
        adresse = R.string.adresse_USTM,
        web = R.string.web_USTM,
        ville = R.string.fcv,
        description = R.string.description_USTM,
        domaines = domainesUSTM,
        telephone = listOf(
            "+241 74 42 38 21",
            "+241 11 67 75 78",
        ),
        logo = R.drawable.logo_ustm
    )
}








val domainesUSTM = listOf(
    "Informatique",
    "Mathématiques",
    "Chimie",
    "Physique",
    "Ingénierie informatique",
    "Génie civil",
    "Ingénierie électronique",
    "Études environnementales",
    "Et plus ..."
)

val domainesINPTIC = listOf(
    "informatique",
    "audiovisuel",
    "multimédia",
    "Internet",
    "télécommunications"
)

val domainesUOB = listOf(
    "Économie",
    "Loi",
    "Psychologie",
    "Histoire",
    "Sciences sociales",
    "Tourisme",
    "Littérature",
    "Philosophie",
    "Et plus..."
)