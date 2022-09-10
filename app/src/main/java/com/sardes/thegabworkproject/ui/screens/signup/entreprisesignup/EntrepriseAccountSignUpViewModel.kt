package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup

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
import com.sardes.thegabworkproject.repository.signuprepository.EntrepriseSignUpRepository
import com.sardes.thegabworkproject.repository.signuprepository.common.CommonSignUpRepository
import kotlinx.coroutines.launch

class EntrepriseAccountSignUpViewModel(
    private val repository: EntrepriseSignUpRepository = EntrepriseSignUpRepository(),
    private val commonSignUpRepository: CommonSignUpRepository = CommonSignUpRepository()
): ViewModel() {

    val currentUser = commonSignUpRepository.currentUser

    val hasUser: Boolean
        get() = commonSignUpRepository.hasUser()

    private val entreprise: FirebaseUser?
        get() = commonSignUpRepository.user()

    var signUpUiStateEntreprise by mutableStateOf(SignupUiStateEntreprise())
        private set


    private fun validateEntrepriseForm() =
        signUpUiStateEntreprise.entrepriseMail.isNotBlank() &&
                signUpUiStateEntreprise.password.isNotBlank() &&
                signUpUiStateEntreprise.confirmPassword.isNotBlank() &&
                signUpUiStateEntreprise.entrepriseName.isNotBlank() &&
                signUpUiStateEntreprise.address.isNotBlank() &&
                signUpUiStateEntreprise.city.isNotEmpty() &&
                signUpUiStateEntreprise.phone.isNotBlank() &&
                signUpUiStateEntreprise.activityArea.isNotBlank() &&
                signUpUiStateEntreprise.description.isNotBlank()
    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateEntrepriseForm()){
                throw IllegalArgumentException("Les champs marqués d'une étoile (*) doivent être remplis")
            }

            signUpUiStateEntreprise = signUpUiStateEntreprise.copy(isLoading = true)

            if (signUpUiStateEntreprise.password !=
                signUpUiStateEntreprise.confirmPassword){
                throw  IllegalArgumentException(
                    "Les mots de passe ne correspondent pas"
                )
            }

            signUpUiStateEntreprise = signUpUiStateEntreprise.copy(signUpError = null)

            commonSignUpRepository.createUser(
                signUpUiStateEntreprise.entrepriseMail,
                signUpUiStateEntreprise.password
            ){ isSuccessful ->
                if (isSuccessful){

                    // ADD DATA TO FIRESTORE RIGHT AFTER CREATING ACCOUNT
                    addUserInformations()

                    Toast.makeText(
                        context,
                        "Compte créé avec succès",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpUiStateEntreprise = signUpUiStateEntreprise.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Erreur lors de la création du compte",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpUiStateEntreprise = signUpUiStateEntreprise.copy(isSuccessLogin = false)
                }
            }
        }catch (e:Exception){
            signUpUiStateEntreprise = signUpUiStateEntreprise.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            signUpUiStateEntreprise = signUpUiStateEntreprise.copy(isLoading = false)
        }
    }


    private fun addUserInformations(){
        if (hasUser){
            repository.addEntrepriseInformations(
                entrepriseId = entreprise!!.uid,
                nom = signUpUiStateEntreprise.entrepriseName,
                telephone = signUpUiStateEntreprise.phone,
                email = signUpUiStateEntreprise.entrepriseMail,
                ville = signUpUiStateEntreprise.city,
                activite = signUpUiStateEntreprise.activityArea,
                description = signUpUiStateEntreprise.description,
                adresse = signUpUiStateEntreprise.address,
                siteWeb = signUpUiStateEntreprise.website,
                urlLogo = "https://" +
                        "firebasestorage.googleapis.com/v0/b/" +
                        "thegabworkprojecttest.appspot.com/o/" +
                        "userProfile%2Fentreprise%2F" +
                        "${entreprise!!.uid}__profile__entreprise.jpg" +
                        "?alt=media",
                logoEntreprise = signUpUiStateEntreprise.logo,
                dateCreationCompte = Timestamp.now(),
                dateCreationEntreprise = signUpUiStateEntreprise.creationDate,
                employes = signUpUiStateEntreprise.employes
            ){
                signUpUiStateEntreprise = signUpUiStateEntreprise.copy(informationsAddedStatus = it)
            }
        }
    }


    fun onEntrepriseEmailChangeSignUp(entrepriseMail: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(entrepriseMail = entrepriseMail)
    }

    fun onPasswordChangeSignUp(password: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(confirmPassword = confirmPassword)
    }

    fun onEntrerpiseNameChange(entrepriseName: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(entrepriseName = entrepriseName)
    }

    fun onEntreprisePhoneChange(entreprisePhone: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(phone = entreprisePhone)
    }

    fun onCityChange(city: List<String>){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(city = city)
    }

    fun onAddressChange(address: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(address = address)
    }

    fun onActivityAreaChange(activityArea: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(activityArea = activityArea)
    }

    fun onEntrepriseDescriptionChange(description: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(description = description)
    }

    fun onWebsiteChange(website: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(website = website)
    }

    fun onEmployesChange(employes: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(employes = employes)
    }


    fun onLogoChange(logo: Uri?){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(logo = logo)
    }

    fun onCreationDateChange(creationDate: String){
        signUpUiStateEntreprise = signUpUiStateEntreprise.copy(creationDate = creationDate)
    }
}


data class SignupUiStateEntreprise(
    val entrepriseMail  : String = "",
    val password        : String = "",
    val confirmPassword : String = "",

    val entrepriseName  : String = "",
    val phone           : String = "",
    val activityArea    : String = "",
    val description     : String = "",
    val website         : String = "",
    val city            : List<String> = emptyList(),
    val address         : String = "",
    val logo            : Uri? = null,
    val creationDate    : String = "",
    val employes        : String = "",

//STATES
    val isLoading       : Boolean = false,
    val isSuccessLogin  : Boolean = false,
    val signUpError     : String? = null,
    val loginError      : String? = null,

    val informationsAddedStatus: Boolean = false,

    )
