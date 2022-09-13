package com.sardes.thegabworkproject.test

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.StandardSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.standardsignup.SignupUiStateStandard


@SuppressLint("MaterialDesignInsteadOrbitDesign")
@Preview(showBackground = true)
@Composable
fun Test(
    uiState: SignupUiStateStandard? = null,
    viewModel: StandardSignUpViewModel? = null,
    focusRequester: FocusRequester = FocusRequester.Default
) {


    LazyColumn(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(30.dp)
    ) {

        item {

        }



        item {

        }
    }

}

