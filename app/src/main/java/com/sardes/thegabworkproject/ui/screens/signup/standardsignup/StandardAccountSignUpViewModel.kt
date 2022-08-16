package com.sardes.thegabworkproject.ui.screens.signup.standardsignup

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.sardes.thegabworkproject.repository.signuprepository.StandardSignUpRepository
import com.sardes.thegabworkproject.repository.signuprepository.common.CommonSignUpRepository
import kotlinx.coroutines.launch

class StandardAccountSignUpViewModel(
    private val repository: StandardSignUpRepository = StandardSignUpRepository(),
    private val commonSignUpRepository: CommonSignUpRepository = CommonSignUpRepository()
): ViewModel() {

    val currentUser = commonSignUpRepository.currentUser

    val hasUser: Boolean
        get() = commonSignUpRepository.hasUser()

    private val user: FirebaseUser?
        get() = commonSignUpRepository.user()


    var signUpUiState by mutableStateOf(SignUpUiState())
        private set



    private fun validateSignUpForm() =
        signUpUiState.userMail.isNotBlank() &&
                signUpUiState.password.isNotBlank() &&
                signUpUiState.confirmPassword.isNotBlank() &&
                signUpUiState.userName.isNotBlank() &&
                signUpUiState.foreName.isNotBlank() &&
                signUpUiState.address.isNotBlank() &&
                signUpUiState.city.isNotBlank() &&
                signUpUiState.sex.isNotBlank() &&
                signUpUiState.phone.isNotBlank() &&
                signUpUiState.nationality.isNotBlank()


    fun createUser(context: Context) = viewModelScope.launch {
        try {
            if (!validateSignUpForm()){
                throw IllegalArgumentException("Les champs email et mot de passe doivent être rempli")
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
                signUpUiState.userMail,
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


    fun addUserInformations(){
        if (hasUser){
            repository.addUserInformations(
                userId = user!!.uid,
                userName = signUpUiState.userName,
                userForeName = signUpUiState.foreName,
                userPassword = signUpUiState.password,
                sex = signUpUiState.sex,
                phone = signUpUiState.phone,
                email = signUpUiState.userMail,
                city = signUpUiState.city,
                nationality = signUpUiState.nationality,
                address = signUpUiState.address,
                urlPhoto = signUpUiState.urlProfilePicture,
                timestamp = Timestamp.now()
            ){
                signUpUiState = signUpUiState.copy(informationsAddedStatus = it)
            }
        }
    }



    fun onUserEmailChangeSignUp(userMail: String){
        signUpUiState = signUpUiState.copy(userMail = userMail)
    }

    fun onPasswordChangeSignUp(password: String){
        signUpUiState = signUpUiState.copy(password = password)
    }

    fun onConfirmPasswordChange(confirmPassword: String){
        signUpUiState = signUpUiState.copy(confirmPassword = confirmPassword)
    }

    fun onUserNameChange(userName: String){
        signUpUiState = signUpUiState.copy(userName = userName)
    }

    fun onForeNameChange(foreName: String){
        signUpUiState = signUpUiState.copy(foreName = foreName)
    }

    fun onUserSexChange(userSex: String){
        signUpUiState = signUpUiState.copy(sex = userSex)
    }

    fun onUserPhoneChange(userPhone: String){
        signUpUiState = signUpUiState.copy(phone = userPhone)
    }

    fun onUserCityChange(userCity: String){
        signUpUiState = signUpUiState.copy(city = userCity)
    }

    fun onNationalityChange(nationality: String){
        signUpUiState = signUpUiState.copy(nationality = nationality)
    }

    fun onAddressChange(address: String){
        signUpUiState = signUpUiState.copy(address = address)
    }

    fun onUrlProfilPictureChange(urlProfilePicture: String){
        signUpUiState = signUpUiState.copy(urlProfilePicture = urlProfilePicture)
    }
}


data class SignUpUiState(
    val userMail: String = "",
    val password: String = "",
    val confirmPassword: String = "",

    val userName: String = "",
    val foreName: String = "",
    val sex: String = "",
    val phone: String = "",
    val city: String = "",
    val nationality: String = "",
    val address: String = "",
    val urlProfilePicture: String = "",

    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null,

    val informationsAddedStatus: Boolean = false,

    )
