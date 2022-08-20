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

    val currenUser = commonSignUpRepository.currentUser

    val hasUser: Boolean
        get() = commonSignUpRepository.hasUser()

    private val entreprise: FirebaseUser?
        get() = commonSignUpRepository.user()

    var signUpUiState by mutableStateOf(SignupUiState())
        private set


    private fun validateEntrepriseForm() =
        signUpUiState.entrepriseMail.isNotBlank() &&
                signUpUiState.password.isNotBlank() &&
                signUpUiState.confirmPassword.isNotBlank() &&
                signUpUiState.entrepriseName.isNotBlank() &&
                signUpUiState.address.isNotBlank() &&
                signUpUiState.city.isNotBlank() &&
                signUpUiState.phone.isNotBlank() &&
                signUpUiState.activityArea.isNotBlank() &&
                signUpUiState.description.isNotBlank()


    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateEntrepriseForm()){
                throw IllegalArgumentException("Les champs marqués d'une étoiles (*) doivent être rempli")
            }

            signUpUiState = signUpUiState.copy(isLoading = true)

            if (signUpUiState.password !=
                signUpUiState.confirmPassword){
                throw  IllegalArgumentException(
                    "Les mots de passe ne correspondent pas"
                )
            }

            signUpUiState = signUpUiState.copy(signUpError = null)

            commonSignUpRepository.createUser(
                signUpUiState.entrepriseMail,
                signUpUiState.password
            ){ isSuccessful ->
                if (isSuccessful){

                    // ADD DATA TO FIRESTORE RIGHT AFTER CREATING ACCOUNT
                    addUserInformations()

                    Toast.makeText(
                        context,
                        "Compte créé avec succès",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpUiState = signUpUiState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Erreur lors de la création du compte",
                        Toast.LENGTH_SHORT
                    ).show()
                    signUpUiState = signUpUiState.copy(isSuccessLogin = false)
                }
            }
        }catch (e:Exception){
            signUpUiState = signUpUiState.copy(signUpError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            signUpUiState = signUpUiState.copy(isLoading = false)
        }
    }


    private fun addUserInformations(){
        if (hasUser){
            repository.addEntrepriseInformations(
                entrepriseId = entreprise!!.uid,
                entrepriseName = signUpUiState.entrepriseName,
                entreprisePassword = signUpUiState.password,
                phone = signUpUiState.phone,
                email = signUpUiState.entrepriseMail,
                city = signUpUiState.city,
                activityArea = signUpUiState.activityArea,
                description = signUpUiState.description,
                address = signUpUiState.address,
                website = signUpUiState.website,
                urlLogo = "https://" +
                        "firebasestorage.googleapis.com/v0/b/" +
                        "thegabworkprojecttest.appspot.com/o/" +
                        "userProfile%2Fentreprise%2F" +
                        "${entreprise!!.uid}__profile__entreprise.jpg" +
                        "?alt=media",
                Logo = signUpUiState.logo,
                timestamp = Timestamp.now(),
                creationDate = signUpUiState.creationDate!!,
            ){
                signUpUiState = signUpUiState.copy(informationsAddedStatus = it)
            }
        }
    }


    fun onEntrepriseEmailChangeSignUp(entrepriseMail: String){
        signUpUiState = signUpUiState.copy(entrepriseMail = entrepriseMail)
    }

    fun onPasswordChangeSignUp(password: String){
        signUpUiState = signUpUiState.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String){
        signUpUiState = signUpUiState.copy(confirmPassword = confirmPassword)
    }

    fun onEntrerpiseNameChange(entrepriseName: String){
        signUpUiState = signUpUiState.copy(entrepriseName = entrepriseName)
    }

    fun onEntreprisePhoneChange(entreprisePhone: String){
        signUpUiState = signUpUiState.copy(phone = entreprisePhone)
    }

    fun onEntrepriseCityChange(entrepriseCity: String){
        signUpUiState = signUpUiState.copy(city = entrepriseCity)
    }

    fun onAddressChange(address: String){
        signUpUiState = signUpUiState.copy(address = address)
    }

    fun onEntrepriseActivityAreaChange(activityArea: String){
        signUpUiState = signUpUiState.copy(activityArea = activityArea)
    }

    fun onEntrepriseDescriptionChange(description: String){
        signUpUiState = signUpUiState.copy(description = description)
    }

    fun onEntrepriseWebsiteChange(website: String){
        signUpUiState = signUpUiState.copy(website = website)
    }


    fun onLogoChange(logo: Uri?){
        signUpUiState = signUpUiState.copy(logo = logo)
    }

    fun onCreationDateChange(creationDate: Timestamp?){
        signUpUiState = signUpUiState.copy(creationDate = creationDate)
    }
}


data class SignupUiState(
    val entrepriseMail: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val entrepriseName: String = "",
    val phone: String = "",
    val activityArea: String = "",
    val description: String = "",
    val website: String = "",
    val city: String = "",
    val address: String = "",
    val logo: Uri? = null,
    val creationDate: Timestamp? = null,

//STATES
    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null,

    val informationsAddedStatus: Boolean = false,

    )
