package com.sardes.thegabworkproject.ui.screens.signup.seekersignup

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
import com.sardes.thegabworkproject.repository.signuprepository.SeekerSignUpRepository
import com.sardes.thegabworkproject.repository.signuprepository.common.CommonSignUpRepository
import kotlinx.coroutines.launch

class SeekerSignUpViewModel(
    private val repository: SeekerSignUpRepository = SeekerSignUpRepository(),
    private val commonSignUpRepository: CommonSignUpRepository = CommonSignUpRepository(),
) : ViewModel() {


    val hasUser: Boolean
        get() = commonSignUpRepository.hasUser()

    private val seeker: FirebaseUser?
        get() = commonSignUpRepository.user()

    var signUpUiStateSeeker by mutableStateOf(SignupUiStateSeeker())
        private set

    private fun validateSeekerForm() =
        signUpUiStateSeeker.email.isNotBlank() &&
        signUpUiStateSeeker.nom.isNotBlank() &&
        signUpUiStateSeeker.HQH.isNotBlank() &&
        signUpUiStateSeeker.telephone.isNotBlank() &&
        signUpUiStateSeeker.education.isNotEmpty() &&
        signUpUiStateSeeker.experience.isNotEmpty() &&
        signUpUiStateSeeker.langues.isNotEmpty() &&
        signUpUiStateSeeker.competences.isNotEmpty() &&
        signUpUiStateSeeker.sexe.isNotBlank() &&
        signUpUiStateSeeker.ville.isNotBlank() &&
        signUpUiStateSeeker.nationalite.isNotBlank() &&
        signUpUiStateSeeker.metier.isNotBlank() &&
        signUpUiStateSeeker.adresse.isNotBlank()


    fun createUser(context: Context) = viewModelScope.launch{
        try {
            if (!validateSeekerForm()){
                throw IllegalArgumentException("Les champs marqués d'une étoile (*) doivent être remplis")
            }

            signUpUiStateSeeker = signUpUiStateSeeker.copy(isLoading = true)

            if (signUpUiStateSeeker.password != signUpUiStateSeeker.confirmPassword){
                throw IllegalArgumentException("Les mots de passes ne correspondent pas")
            }

            signUpUiStateSeeker = signUpUiStateSeeker.copy(signUpError = null)

            commonSignUpRepository.createUser(
                signUpUiStateSeeker.email,
                signUpUiStateSeeker.password
            ){isSuccessful ->
                if (isSuccessful){

                    addUserInformations()

                    Toast.makeText(
                        context,
                        "Vous êtes désormais gabworker",
                        Toast.LENGTH_SHORT
                    ).show()

                    signUpUiStateSeeker = signUpUiStateSeeker.copy(isSuccessLogin = false)
                }else{
                    Toast.makeText(
                        context,
                        "Erreur lors de la création du compte",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpUiStateSeeker = signUpUiStateSeeker.copy(isSuccessLogin = false)
                }
            }
        }catch (e: Exception){
            signUpUiStateSeeker = signUpUiStateSeeker.copy(signUpError = e.localizedMessage)
        }finally {
            signUpUiStateSeeker = signUpUiStateSeeker.copy(isLoading = false)
        }
    }



    private fun addUserInformations(){
        if (hasUser){
            repository.addUserInformations(
                userId              = seeker!!.uid,
                nom                 = signUpUiStateSeeker.nom,
                prenom              = signUpUiStateSeeker.prenom,
                email               = signUpUiStateSeeker.email,
                villes              = signUpUiStateSeeker.ville,
                sexe                = signUpUiStateSeeker.sexe,
                nationalite         = signUpUiStateSeeker.nationalite,
                metier              = signUpUiStateSeeker.metier,
                adresse             = signUpUiStateSeeker.adresse,
                langues             = signUpUiStateSeeker.langues,
                competences         = signUpUiStateSeeker.competences,
                HQH                 = signUpUiStateSeeker.HQH,
                telephone           = signUpUiStateSeeker.telephone,
                photo               = signUpUiStateSeeker.photo,
                education           = signUpUiStateSeeker.education,
                experience          = signUpUiStateSeeker.experience,
                dateNaissance       = signUpUiStateSeeker.dateNaissance,
                preferencesDEmploi  = signUpUiStateSeeker.preferencesDEmploi,
                dateCreationCompte  = Timestamp.now(),
                urlPhotoProfil      = "https://" +
                        "firebasestorage.googleapis.com/v0/b/" +
                        "thegabworkprojecttest.appspot.com/o/" +
                        "userProfile%2Fdemandeur%2F" +
                        "${seeker!!.uid}__profile__demandeur.jpg" +
                        "?alt=media",

                urlCV = "https://" +
                        "firebasestorage.googleapis.com/v0/b/" +
                        "thegabworkprojecttest.appspot.com/o/" +
                        "userCVs%2Fdemandeur%2F" +
                        "${seeker!!.uid}__cv__demandeur.jpg" +
                        "?alt=media",
            ){
                signUpUiStateSeeker = signUpUiStateSeeker.copy(informationsAddedStatus = it)
            }
        }
    }



    fun onNomChange(nom: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(nom = nom)
    }

    fun onPrenomChange(prenom: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(prenom = prenom)
    }

    fun onSexeChange(sexe: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(sexe = sexe)
    }

    fun onHQHChange(HQH: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(HQH = HQH)
    }

    fun onTelephoneChange(telephone: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(telephone = telephone)
    }

    fun onEmailChange(email: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(email = email)
    }

    fun onVilleChange(ville: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(ville = ville)
    }

    fun onNationaliteChange(nationalite: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(nationalite = nationalite)
    }

    fun onadresseChange(adresse: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(adresse = adresse)
    }

    fun onOccupationChange(occupation: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(metier = occupation)
    }

    fun onPreferencesDEmploiChange(preferencesDEmploi: List<String>){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(preferencesDEmploi = preferencesDEmploi)
    }

    fun onLanguesChange(langues: List<String>){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(langues = langues)
    }

    fun onCompetencesChange(competences: List<Skill>){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(competences = competences)
    }

    fun onPasswordChange(password: String) {
        signUpUiStateSeeker = signUpUiStateSeeker.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        signUpUiStateSeeker = signUpUiStateSeeker.copy(confirmPassword = confirmPassword)
    }

    fun onPhotoChange(photo: Uri?){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(photo = photo)
    }

    fun onEducationChange(education: List<Education>){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(education = education)
    }

    fun onExperienceChange(experience: List<Experience>){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(experience = experience)
    }
    fun onDateNaissanceChange(dateNaissance: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(dateNaissance = dateNaissance)
    }
    fun onMetierChange(metier: String){
        signUpUiStateSeeker = signUpUiStateSeeker.copy(metier = metier)
    }
}

data class SignupUiStateSeeker(

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