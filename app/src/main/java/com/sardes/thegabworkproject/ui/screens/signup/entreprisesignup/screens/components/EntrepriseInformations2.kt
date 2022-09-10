package com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.screens.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.EntrepriseAccountSignUpViewModel
import com.sardes.thegabworkproject.ui.screens.signup.entreprisesignup.SignupUiStateEntreprise
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import kiwi.orbit.compose.ui.controls.TextField
import java.util.*
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EntrepriseInformations2(
    entrepriseUiState: SignupUiStateEntreprise?,
    viewModel: EntrepriseAccountSignUpViewModel?,
    focusRequester: FocusRequester = FocusRequester.Default,
) {

    val employesOptions = listOf(
        "1 - 50",
        "51 - 100",
        "101 - 200",
        "201 - 500",
        "501 - 1000",
        "plus de 1000",
    )
    var employesExpanded by remember { mutableStateOf(false) }
    var selectedEmployesNumber by remember { mutableStateOf(employesOptions[0]) }


    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
        }, year, month, day
    )


    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(30.dp)
    ) {

        LazyColumn(verticalArrangement = Arrangement.Center) {

//            # WEBSITE
            item {
                TextFieldAuthItem(
                    value = entrepriseUiState?.website ?: "",
                    valueChange = { viewModel?.onWebsiteChange(website = it) },
                    label = "Site web",
                    info = "Site web officiel de l'entreprise s'il existe",
                    focusRequester = focusRequester
                )
            }

            item {
                ExposedDropdownMenuBox(
                    expanded = employesExpanded,
                    onExpandedChange = {
                        employesExpanded = !employesExpanded
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedEmployesNumber,
                        onValueChange = {  },
                        label = { OrbitText("Nombre d'employés") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = employesExpanded
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        modifier = Modifier.background(Color.Transparent),
                        expanded = employesExpanded,
                        onDismissRequest = { employesExpanded = false }
                    ) {
                        employesOptions.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedEmployesNumber = selectionOption
                                    employesExpanded = false
                                }
                            ) {
                                OrbitText(text = selectionOption)
                                viewModel?.onEmployesChange(selectionOption)
                            }
                        }
                    }
                }

            }
//            # ENTREPRISE CREATION DATE
            item {
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                    OrbitText(
                        text = "Date de création de l'entreprise",
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        OrbitText(text = date.value, fontWeight = FontWeight.Bold)

                        Spacer(modifier = Modifier.size(16.dp))

                        ButtonSecondary(onClick = { datePickerDialog.show() }) {
                            OrbitText(text = "Sélectionner")
                        }
                        viewModel?.onCreationDateChange(date.value)
                    }
                }

            }

//            # DESCRIPTION
            item {
                TextFieldAuthItem(
                    value = entrepriseUiState?.description ?: "",
                    valueChange = {viewModel?.onEntrepriseDescriptionChange(it)},
                    label = "Description",
                    info = "Décrivez en quelque phrases votre entreprise",
                    focusRequester = focusRequester
                )
            }

        }
    }

}


@Preview(name = "EntrepriseInformations2")
@Composable
private fun PreviewEntrepriseInformations2() {
    EntrepriseInformations2(null, null)
}