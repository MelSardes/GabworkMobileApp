package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.sardes.thegabworkproject.data.models.CompteEntreprise
import com.sardes.thegabworkproject.repository.main.entreprise.ProfileEntreprisseRepository

class ProfileEntrepriseViewModel(
    private val repository: ProfileEntreprisseRepository = ProfileEntreprisseRepository()
): ViewModel() {

    var informationsUiState by mutableStateOf(InformationsUiState())
        private set

    val hasUser: Boolean
        get() = repository.hasUser()

    private val userId: String
        get() = repository.getUserId()


    fun onNomEntrepriseChange(nomEntreprise: String){
        informationsUiState = informationsUiState.copy(nomEntreprise = nomEntreprise)
    }
    fun onSecteurDActiviteChange(secteurDActivite: String){
        informationsUiState = informationsUiState.copy(secteurDActivite = secteurDActivite)
    }
    fun onDescriptionEntrepriseChange(descriptionEntreprise: String){
        informationsUiState = informationsUiState.copy(descriptionEntreprise = descriptionEntreprise)
    }
    fun onVilleChange(ville: String){
        informationsUiState = informationsUiState.copy(ville = ville)
    }
    fun onEmailEntrepriseChange(emailEntreprise: String){
        informationsUiState = informationsUiState.copy(emailEntreprise = emailEntreprise)
    }
    fun onTelephoneChange(telephone: String){
        informationsUiState = informationsUiState.copy(telephone = telephone)
    }
    fun onAdressEntrepriseChange(adressEntreprise: String){
        informationsUiState = informationsUiState.copy(adressEntreprise = adressEntreprise)
    }
    fun onSiteWebEntrepriseChange(siteWebEntreprise: String){
        informationsUiState = informationsUiState.copy(siteWebEntreprise = siteWebEntreprise)
    }
    fun onUrlLogoEntrepriseChange(urlLogoEntreprise: String){
        informationsUiState = informationsUiState.copy(urlLogoEntreprise = urlLogoEntreprise)
    }
    fun onNomEntreprisChange(nomEntreprise: String){
        informationsUiState = informationsUiState.copy(nomEntreprise = nomEntreprise)
    }



    private fun setEditFields(entreprise: CompteEntreprise){
        informationsUiState = informationsUiState.copy(
            nomEntreprise = entreprise.nom,
            secteurDActivite = entreprise.activite,
            descriptionEntreprise = entreprise.description,
            ville = entreprise.ville,
            emailEntreprise = entreprise.email,
            siteWebEntreprise = entreprise.siteWeb,
            urlLogoEntreprise = entreprise.urlLogo,
            dateCreationCompte = entreprise.dateCreationCompte!!,
        )
    }


    fun loadInformations(){
        if(hasUser){
            getInformations(userId)
        }
    }

    private fun getInformations(entrepriseId: String){
        repository.getEntrepriseInformations(
            entrepriseId = entrepriseId,
            onError = {}
        ){
            informationsUiState = informationsUiState.copy(currentUserEntreprise = it)
//            informationsUiState.currentUserEntreprise?.let{it1 -> setEditFields(it1)}
        }
    }

    fun updateInformations(entrepriseId: String){
        repository.updateData(
            idCompteEntreprise = entrepriseId,
            nomEntreprise = informationsUiState.nomEntreprise,
            secteurDActivite = informationsUiState.secteurDActivite,
            descriptionEntreprise = informationsUiState.descriptionEntreprise,
            ville = informationsUiState.ville,
            emailEntreprise = informationsUiState.emailEntreprise,
            telephone = informationsUiState.telephone,
            adresseEntreprise = informationsUiState.adressEntreprise,
            siteWebEntreprise = informationsUiState.siteWebEntreprise!!,
            urlLogoEntreprise = informationsUiState.urlLogoEntreprise!!,
            dateCreationCompte = informationsUiState.dateCreationCompte!!,
            dateCreationEntreprise = informationsUiState.dateCreationEntreprise!!,
            typeDeCompte = "Entreprise",
        ){
            informationsUiState = informationsUiState.copy(updatedAddedInformations = it)
        }
    }
}

data class InformationsUiState(
    val nomEntreprise: String = "",
    val secteurDActivite: String = "",
    val descriptionEntreprise: String = "",
    val ville: String = "",
    val emailEntreprise: String = "",
    val telephone: String = "",
    val adressEntreprise: String = "",
    val siteWebEntreprise: String? = "",
    val urlLogoEntreprise: String? = "",
    val dateCreationCompte: Timestamp? = null,
    val dateCreationEntreprise: Timestamp? = null,

    val currentUserEntreprise: CompteEntreprise? = null,

    val updatedAddedInformations: Boolean = false,
)