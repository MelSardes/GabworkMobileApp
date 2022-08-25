package com.sardes.thegabworkproject.data.models

class Messages(){
    data class Message_Independant_Entreprise(
        val id_message: Int,
        val id_destinataire: Int,
        val id_destinateur: Int,
        val contenu_message: String,
        val date_envoi: String
    )

    data class Message_Etudiant_Entreprise(
        val id_message: Int,
        val id_destinataire: Int,
        val id_destinateur: Int,
        val contenu_message: String,
        val date_envoi: String
    )

    data class Message_Demandeur_Entreprise(
        val id_message: Int,
        val id_destinataire: Int,
        val id_destinateur: Int,
        val contenu_message: String,
        val date_envoi: String
    )

    data class Message_Standard_Independant(
        val id_message: Int,
        val id_destinataire: Int,
        val id_destinateur: Int,
        val contenu_message: String,
        val date_envoi: String
    )
}
