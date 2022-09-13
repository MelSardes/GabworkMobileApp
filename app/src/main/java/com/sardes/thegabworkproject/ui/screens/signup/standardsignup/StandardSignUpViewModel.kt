package com.sardes.thegabworkproject.ui.screens.signup.standardsignup

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.sardes.thegabworkproject.data.models.Education
import com.sardes.thegabworkproject.data.models.Experience
import com.sardes.thegabworkproject.data.models.Skill
import com.sardes.thegabworkproject.repository.signuprepository.StandardSignUpRepository
import com.sardes.thegabworkproject.repository.signuprepository.common.CommonSignUpRepository
import kotlinx.coroutines.launch

class StandardSignUpViewModel(
    private val repository: StandardSignUpRepository = StandardSignUpRepository(),
    private val commonSignUpRepository: CommonSignUpRepository = CommonSignUpRepository(),
) : ViewModel() {


    val hasUser: Boolean
        get() = commonSignUpRepository.hasUser()

    private val user: FirebaseUser?
        get() = commonSignUpRepository.user()

    var signUpUiStateStandard by mutableStateOf(SignupUiStateStandard())
        private set

    private fun validateSeekerForm() =
        signUpUiStateStandard.email.isNotBlank() &&
        signUpUiStateStandard.nom.isNotBlank() &&
        signUpUiStateStandard.telephone.isNotBlank() &&
        signUpUiStateStandard.sexe.isNotBlank() &&
        signUpUiStateStandard.ville.isNotBlank() &&
        signUpUiStateStandard.nationalite.isNotBlank() &&
        signUpUiStateStandard.adresse.isNotBlank()


    fun createUser(context: Context) = viewModelScope.launch{
        try {
            if (!validateSeekerForm()){
                throw IllegalArgumentException("Les champs marqués d'une étoile (*) doivent être remplis")
            }

            signUpUiStateStandard = signUpUiStateStandard.copy(isLoading = true)

            if (signUpUiStateStandard.password != signUpUiStateStandard.confirmPassword){
                throw IllegalArgumentException("Les mots de passes ne correspondent pas")
            }

            signUpUiStateStandard = signUpUiStateStandard.copy(signUpError = null)

            commonSignUpRepository.createUser(
                signUpUiStateStandard.email,
                signUpUiStateStandard.password
            ){isSuccessful ->
                if (isSuccessful){

                    addUserInformations()

                    Toast.makeText(
                        context,
                        "Vous êtes désormais gabworker",
                        Toast.LENGTH_SHORT
                    ).show()

                    signUpUiStateStandard = signUpUiStateStandard.copy(isSuccessLogin = false)
                }else{
                    Toast.makeText(
                        context,
                        "Erreur lors de la création du compte",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpUiStateStandard = signUpUiStateStandard.copy(isSuccessLogin = false)
                }
            }
        }catch (e: Exception){
            signUpUiStateStandard = signUpUiStateStandard.copy(signUpError = e.localizedMessage)
        }finally {
            signUpUiStateStandard = signUpUiStateStandard.copy(isLoading = false)
        }
    }



    private fun addUserInformations(){
        if (hasUser){
            repository.addUserInformations(
                userId              = user!!.uid,
                nom                 = signUpUiStateStandard.nom,
                prenom              = signUpUiStateStandard.prenom,
                email               = signUpUiStateStandard.email,
                ville              = signUpUiStateStandard.ville,
                sexe                = signUpUiStateStandard.sexe,
                nationalite         = signUpUiStateStandard.nationalite,
                adresse             = signUpUiStateStandard.adresse,
                telephone           = signUpUiStateStandard.telephone,
                photo               = signUpUiStateStandard.photo,
                dateNaissance       = signUpUiStateStandard.dateNaissance,
                dateCreationCompte  = Timestamp.now(),
                urlPhotoProfil      = "https://" +
                        "firebasestorage.googleapis.com/v0/b/" +
                        "thegabworkprojecttest.appspot.com/o/" +
                        "userProfile%2Fstandard%2F" +
                        "${user!!.uid}__profile.jpg" +
                        "?alt=media",
            ){
                signUpUiStateStandard = signUpUiStateStandard.copy(informationsAddedStatus = it)
            }
        }
    }



    fun onNomChange(nom: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(nom = nom)
    }

    fun onPrenomChange(prenom: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(prenom = prenom)
    }

    fun onSexeChange(sexe: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(sexe = sexe)
    }

    fun onHQHChange(HQH: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(HQH = HQH)
    }

    fun onTelephoneChange(telephone: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(telephone = telephone)
    }

    fun onEmailChange(email: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(email = email)
    }

    fun onVilleChange(ville: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(ville = ville)
    }

    fun onNationaliteChange(nationalite: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(nationalite = nationalite)
    }

    fun onadresseChange(adresse: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(adresse = adresse)
    }

    fun onOccupationChange(occupation: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(metier = occupation)
    }

    fun onPreferencesDEmploiChange(preferencesDEmploi: List<String>){
        signUpUiStateStandard = signUpUiStateStandard.copy(preferencesDEmploi = preferencesDEmploi)
    }

    fun onLanguesChange(langues: List<String>){
        signUpUiStateStandard = signUpUiStateStandard.copy(langues = langues)
    }

    fun onCompetencesChange(competences: List<Skill>){
        signUpUiStateStandard = signUpUiStateStandard.copy(competences = competences)
    }

    fun onPasswordChange(password: String) {
        signUpUiStateStandard = signUpUiStateStandard.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        signUpUiStateStandard = signUpUiStateStandard.copy(confirmPassword = confirmPassword)
    }

    fun onPhotoChange(photo: Uri?){
        signUpUiStateStandard = signUpUiStateStandard.copy(photo = photo)
    }

    fun onEducationChange(education: List<Education>){
        signUpUiStateStandard = signUpUiStateStandard.copy(education = education)
    }

    fun onExperienceChange(experience: List<Experience>){
        signUpUiStateStandard = signUpUiStateStandard.copy(experience = experience)
    }
    fun onDateNaissanceChange(dateNaissance: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(dateNaissance = dateNaissance)
    }
    fun onMetierChange(metier: String){
        signUpUiStateStandard = signUpUiStateStandard.copy(metier = metier)
    }
}

data class SignupUiStateStandard(

    val password: String = "",
    val confirmPassword: String = "",



    val nom         : String = "",
    val prenom      : String = "",
    val HQH         : String = "",
    val langues     : List<String> = emptyList(),
    val competences : List<Skill> = emptyList(),
    val education   : List<Education> = emptyList(),
    val experience  : List<Experience> = emptyList(),
    val sexe        : String = "",
    val telephone   : String = "",
    val email       : String = "",
    val ville       : String = "",
    val nationalite : String = "",
    val adresse     : String = "",
    val urlPhoto    : String = "",
    val photo       : Uri? = null,
    val urlCV       : String? = "",
    val metier      : String = "",
    val dateNaissance: String = "",
    val preferencesDEmploi : List<String> = emptyList(),

    val informationsAddedStatus: Boolean = false,


    val isLoading       : Boolean = false,
    val isSuccessLogin  : Boolean = false,
    val signUpError     : String? = null,
    val loginError      : String? = null,

    )