package com.sardes.thegabworkproject.ui.screens.signup.seekersignup.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.SeekerSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.seekersignup.SignupUiStateSeeker
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.PasswordTextField
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TextField

@Composable
fun SeekerAccountDetails(
    focusRequester: FocusRequester,
    uiState: SignupUiStateSeeker?,
    viewModel: SeekerSignUpViewModel?,
) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(30.dp)) {
        //       # EMAIL
        TextField(
            value = uiState?.email ?: "",
            onValueChange = { viewModel?.onEmailChange(it) },
            label = { Text("Email") },
            info = { Text("Ce mail ne peut être utilisée qu'une seule fois sur la plateforme") },
            leadingIcon = {
                Icon(
                    Icons.Email,
                    contentDescription = null
                )
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            placeholder = { Text("Ex: exemple@exemple.com") }
        )

        Spacer(modifier = Modifier.height(30.dp))

//        # PASSWORD
        PasswordTextField(
            value = uiState?.password ?: "",
            onValueChange = { viewModel?.onPasswordChange(it) },
            label = { Text("Mot de passe") },
            info = { Text("8 caractères minimum") },
            leadingIcon = {
                Icon(
                    Icons.Lock,
                    contentDescription = null
                )
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )

        Spacer(modifier = Modifier.height(30.dp))

//        # CONFRIM PASSWORD
        PasswordTextField(
            value = uiState?.confirmPassword ?: "",
            onValueChange = { viewModel?.onConfirmPasswordChange(it) },
            label = { Text("Confirmer le mot de passe") },
            info = { Text("Saisissez de nouveau votre mot de passe") },
            leadingIcon = {
                Icon(
                    Icons.Lock,
                    contentDescription = null
                )
            },
            singleLine = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
        )
    }
}
