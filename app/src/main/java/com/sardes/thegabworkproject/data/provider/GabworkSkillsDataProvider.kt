package com.sardes.thegabworkproject.data.provider

import com.sardes.thegabworkproject.data.models.Competence

object GabworkSkillsDataProvider {

    val competences = mutableListOf(

        Competence(
            nomCompetence = "Autonomie",
            "Selon son expérience, requiert peu de supervision; " +
                    "se débrouille bien pour trouver l’information requise et " +
                    "utilise les ressources disponibles afin de terminer ses projets."
        ),

        Competence(
            nomCompetence = "Éthique + Sens des responsabilités",
            "Prend son travail au sérieux; agit selon les " +
                    "règles qui régissent sa future profession; limite " +
                    "la portée de ses actions et de ses décisions à " +
                    "son niveau de compétence."
        ),

        Competence(
            nomCompetence = "Initiative",
            "Fait preuve de proactivité dans ses actions;" +
                    "Influence les événements pour atteindre les" +
                    "objectifs prévus; ne dépend pas des autres pour" +
                    "prendre des décisions pertinentes et agir."
        ),

        Competence(
            nomCompetence = "Intérêt et motivation",
            "Démontre une attitude positive de son travail." +
                    "Fait preuve de curiosité et d'intérêt; maintient" +
                    "son enthousiasme même dans l’exécution de" +
                    "tâches moins intéressantes"
        ),

        Competence(
            nomCompetence = "Efficacité et efficience",
            "Livre ce qui est attendu dans le délai prévu, tout" +
                    "en respectant les normes établies; est orienté" +
                    "vers les résultats."
        ),

        Competence(
            nomCompetence = "Gestion de la qualité",
            "Porte une attention adéquate aux détails dans" +
                    "la réalisation de son travail; agit avec rigueur et" +
                    "minutie; respecte les critères de quantité et de" +
                    "qualité établis; révise son travail avant de le remettre"
        )
    )


}

