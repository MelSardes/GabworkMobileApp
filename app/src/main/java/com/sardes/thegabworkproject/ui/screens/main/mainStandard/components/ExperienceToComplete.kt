package com.sardes.thegabworkproject.ui.screens.main.mainStandard.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sardes.thegabworkproject.data.models.CompteStandard
import com.sardes.thegabworkproject.ui.composition.TextFieldAuthItem
import com.sardes.thegabworkproject.ui.screens.components.ExperienceCard
import com.sardes.thegabworkproject.ui.screens.main.mainStandard.home.HomeStandardViewModel
import kiwi.orbit.compose.ui.controls.ButtonPrimary
import kiwi.orbit.compose.ui.controls.SegmentedSwitch
import kiwi.orbit.compose.ui.controls.Text

@Composable
fun ExperienceToComplete(
    viewModel: HomeStandardViewModel?,
    experiences: MutableList<CompteStandard.Experience>
) {

    var companyName by remember { mutableStateOf("") }
    var position by remember { mutableStateOf("") }
    var jobType by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }

    var selectedIndex by remember { mutableStateOf<Int?>(null) }




    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 30.dp)
            .fillMaxWidth()
    ) {
        item {
            ExperienceCard(
                Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                experience = CompteStandard.Experience(
                    entreprise = companyName,
                    position = position,
                    typeDEmploi = jobType,
                    description = description,
                    ville = city,
                    dateDebut = startDate,
                    dateFin = endDate
                )
            )
        }

        item {
            TextFieldAuthItem(
                label = "Nom de l'entreprise",
                info = "Entreprise dans laquelle vous avez travaillé",
                value = companyName,
                valueChange = { companyName = it }
            )
        }
        item {
            TextFieldAuthItem(
                label = "Position",
                info = "Post occupé",
                value = position,
                valueChange = { position = it }
            )
        }


        item {
            SegmentedSwitch(
                optionFirst = { Text("Temps plein") },
                optionSecond = { Text("Temps partiel") },
                selectedIndex = selectedIndex,
                onOptionClick = { index ->
                    jobType = if (index == 1) "Temps partiel" else "Temps plein"
                    selectedIndex = index.takeIf { index != selectedIndex }
                },
                label = { Text("Type d'emploi") },
            )

            Spacer(modifier = Modifier.height(10.dp))
        }



        item {
            TextFieldAuthItem(
                label = "description",
                info = "Informations supplémentaire",
                value = description,
                valueChange = { description = it }
            )
        }
        item {
            TextFieldAuthItem(
                label = "Ville",
                info = "Où se situe l'entreprise",
                value = city,
                valueChange = { city = it }
            )
        }
        item {
            TextFieldAuthItem(
                label = "Date de début",
                info = "Format mm-aaaa",
                value = startDate,
                valueChange = { startDate = it }
            )
        }
        item {
            TextFieldAuthItem(
                label = "Date de fin",
                info = "Format mm-aaaa",
                value = endDate,
                valueChange = { endDate = it }
            )
        }


        item {
            Spacer(modifier = Modifier.height(20.dp))


            ButtonPrimary(onClick = {

                if (
                    city.isNotBlank() &&
                    jobType.isNotBlank() &&
                    position.isNotBlank() &&
                    startDate.isNotBlank() &&
                    description.isNotBlank() &&
                    companyName.isNotBlank() &&
                    endDate.isNotBlank()
                ) {

                    experiences.add(
                        CompteStandard.Experience(
                            companyName,
                            position,
                            jobType,
                            description,
                            city,
                            startDate,
                            endDate
                        )
                    )

                    viewModel?.onExperienceChange(experiences)

                    companyName = ""
                    position = ""
                    jobType = ""
                    description = ""
                    city = ""
                    startDate = ""
                    startDate = ""
                    endDate = ""
                }


            }) {
                Text(text = "Ajouter")
            }
            Spacer(modifier = Modifier.height(50.dp))
        }

        experiences.forEach { experience ->
            item {
                ExperienceCard(
                    Modifier
                        .fillMaxWidth()
                        .width(400.dp),
                    experience = experience
                )
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}


@Preview(name = "ExperienceStandardSignUp", showBackground = true)
@Composable
private fun PreviewExperienceStandardSignUp() {
    ExperienceToComplete(null, emptyList<CompteStandard.Experience>().toMutableStateList())
}