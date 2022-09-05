package com.sardes.thegabworkproject.ui.composition

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kiwi.orbit.compose.ui.controls.ButtonSecondary
import java.util.*
import kiwi.orbit.compose.ui.controls.Text as OrbitText

@Composable
fun ShowDatePicker(textButton: String, label: String) {
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
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
        OrbitText(text = label, fontWeight = FontWeight.SemiBold)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            OrbitText(text = date.value, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.size(16.dp))

            ButtonSecondary(onClick = { datePickerDialog.show() }) {
                OrbitText(text = textButton)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DatePickerPreview() {
    ShowDatePicker(textButton = "Sélectionnez une date", label = "Date de création")
}