package com.sardes.thegabworkproject.ui.screens.signup.standardsignup.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.SignupUiStateStandard
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel

@Composable
fun OtherDetailsStandardSignUp(
    uiState: SignupUiStateStandard?,
    viewModel: StandardSignUpViewModel?,
    focusRequester: FocusRequester = FocusRequester.Default
) {
    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(30.dp)
    ){

//        # ADDRES
        item {
            TextFieldAuthItem(
                label = "Adresse *",
                info = "L'adresse de votre résidence permanente",
                value = uiState?.adresse ?: "",
                valueChange = { viewModel?.onadresseChange(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }


//        # CITY
        item {
            TextFieldAuthItem(
                label = "Ville *",
                info = "Ville de votre résidence",
                value = uiState?.ville ?: "",
                valueChange = { viewModel?.onVilleChange(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }


//        # PHONE
        item{
            TextFieldAuthItem(
                label = "Téléphone *",
                info = "Votre numéro de téléphone format 0xx xx xx xx",
                value = uiState?.telephone ?: "",
                valueChange = { viewModel?.onTelephoneChange(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

////        # HQH
//        item {
//            TextFieldAuthItem(
//                label = "Diplome le plus élevé *",
//                info = "Diplôme le plus élevé obtenu lors de votre parcours scolaire",
//                value = uiState?.HQH ?: "",
//                valueChange = { viewModel?.onHQHChange(it) }
//            )
//            Spacer(modifier = Modifier.height(20.dp))
//        }

////        # POSITION
//        item {
//            TextFieldAuthItem(
//                label = "Métier *",
//                info = "Votre métier, celui que vous métriez sur votre CV",
//                value = uiState?.metier ?: "",
//                valueChange = { viewModel?.onMetierChange(it) }
//            )
//        }
    }

}

@Preview(name = "PersonalInformationsSeekerSignUp", showBackground = true)
@Composable
private fun PreviewPersonalInformationsSeekerSignUp() {
    OtherDetailsStandardSignUp(null, null)
}