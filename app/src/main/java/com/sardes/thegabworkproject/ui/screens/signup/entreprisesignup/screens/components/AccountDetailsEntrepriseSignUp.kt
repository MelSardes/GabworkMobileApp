package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.SignupUiStateEntreprise
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.PasswordTextField

@Composable
fun AccountDetailsEntrepriseSignUp(
    entrepriseUiState: SignupUiStateEntreprise?,
    viewModel: EntrepriseAccountSignUpViewModel?,
    focusRequester: FocusRequester = FocusRequester.Default,
) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(30.dp)) {
        //       # EMAIL

        TextFieldAuthItem(
            label = "Email",
            info = "Ce mail ne peut être utilisée qu'une seule fois sur la plateforme",
            value = entrepriseUiState?.entrepriseMail ?: "",
            valueChange =  { viewModel?.onEntrepriseEmailChangeSignUp(it) }
        )


        Spacer(modifier = Modifier.height(30.dp))

//        # PASSWORD
        PasswordTextField(
            value = entrepriseUiState?.password ?: "",
            onValueChange = { viewModel?.onPasswordChangeSignUp(it) },
            label = { kiwi.orbit.compose.ui.controls.Text("Mot de passe") },
            info = { kiwi.orbit.compose.ui.controls.Text("8 caractères minimum") },
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
            value = entrepriseUiState?.confirmPassword ?: "",
            onValueChange = { viewModel?.onConfirmPasswordChange(it) },
            label = { kiwi.orbit.compose.ui.controls.Text("Confirmer le mot de passe") },
            info = { kiwi.orbit.compose.ui.controls.Text("Saisissez de nouveau votre mot de passe") },
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

@Preview(name = "AccountDetails")
@Composable
private fun PreviewAccountDetails() {
    AccountDetailsEntrepriseSignUp(null, null)
}