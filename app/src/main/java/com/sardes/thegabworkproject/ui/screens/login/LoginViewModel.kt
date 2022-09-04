package com.sardes.thegabworkproject.ui.screens.login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sardes.thegabworkproject.data.models.UserType
import com.sardes.thegabworkproject.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: AuthRepository = AuthRepository()
): ViewModel() {

    val currenUser = repository.currentUser


    val userId: String
        get() = repository.getUserId()


    val hasUser: Boolean
        get() = repository.hasUser()

    var loginUiState by mutableStateOf(LoginUiState())
//        private set

    fun onUserEmailChange(userEmail: String){
        loginUiState = loginUiState.copy(userMail = userEmail)
    }

    fun onPasswordChange(password: String){
        loginUiState = loginUiState.copy(password = password)
    }



    private fun validateLoginForm() =
        loginUiState.userMail.isNotBlank() &&
                loginUiState.password.isNotBlank()
    fun loginUser(context: Context)=viewModelScope.launch {
        try {
            if (!validateLoginForm()){
                throw IllegalArgumentException("Les champs email et le mot de passe ne peuvent pas être vide")
            }
            loginUiState = loginUiState.copy(isLoading = true)

            loginUiState = loginUiState.copy(loginError = null)
            repository.login(
                loginUiState.userMail,
                loginUiState.password
            ){ isSuccessful ->
                if (isSuccessful){
                    Toast.makeText(
                        context,
                        "Connexion au compte réussie",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = true)
                }else{
                    Toast.makeText(
                        context,
                        "Erreur lors de la connexion à votre compte",
                        Toast.LENGTH_SHORT
                    ).show()
                    loginUiState = loginUiState.copy(isSuccessLogin = false)
                }
            }
        }catch (e:Exception){
            loginUiState = loginUiState.copy(loginError = e.localizedMessage)
            e.printStackTrace()
        }finally {
            loginUiState = loginUiState.copy(isLoading = false)
        }
    }


    fun loadUserAccountType(){
        if(hasUser){
            getUserAccountType(userId)
        }
    }

    private fun getUserAccountType(userId: String){
        repository.getUserAccountType(userId = userId, onError = {}){
            loginUiState = loginUiState.copy(userType = it)
        }
    }

}


data class LoginUiState(
    val userMail: String = "",
    val password: String = "",

    val isLoading: Boolean = false,
    val isSuccessLogin: Boolean = false,
    val signUpError: String? = null,
    val loginError: String? = null,

    val userType: UserType? = null
)
