package com.sardes.thegabworkproject.ui.screens.main.mainEntreprise.posts.standalonepost.create

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun ShowDialog(type: DialogType, onDismiss: () -> Unit) {

    when (type) {
        DialogType.DATEPICKER -> {
            val context = LocalContext.current
            (context as? FragmentActivity)?.supportFragmentManager?.let { manager ->

                val builder = MaterialDatePicker.Builder.datePicker()
                    .build()

                builder.addOnPositiveButtonClickListener { selectedDate ->

                }
                builder.addOnDismissListener {
                    onDismiss()
                }
                builder.show(manager, "DatePicker")
            }
        }

        else -> {}
    }

}
