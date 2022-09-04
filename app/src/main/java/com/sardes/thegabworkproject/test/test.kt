import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kiwi.orbit.compose.icons.Icons
import kiwi.orbit.compose.ui.controls.*

@Preview(showSystemUi = true)
@Composable
fun Test() {

    var datumOne by remember { mutableStateOf("") }
    var datumTwo by remember { mutableStateOf("") }
    var datumThree by remember { mutableStateOf("") }

    var isStepOneVisible by remember { mutableStateOf(true) }
    var isStepTwoVisible by remember { mutableStateOf(false) }
    var isStepThreeVisible by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Row(horizontalArrangement = Arrangement.SpaceAround) {
            ButtonSecondary(onClick = {
                isStepOneVisible = true
                isStepTwoVisible = false
                isStepThreeVisible = false
            }) {
                Text("Step 1")
            }
            ButtonSecondary(onClick = {
                isStepOneVisible = false
                isStepTwoVisible = true
                isStepThreeVisible = false
            }) {
                Text("Step 2")
            }
            ButtonSecondary(onClick = {
                isStepOneVisible = false
                isStepTwoVisible = false
                isStepThreeVisible = true
            }) {
                Text("Step 3")
            }
        }

        Column(
            Modifier.padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            val focusRequester = remember { FocusRequester() }

            if (isStepOneVisible) {
                TextField(
                    value = datumOne,
                    onValueChange = { datumOne = it },
                    label = { Text("Something in One") },
                    info = { Text("Something else") },
                    leadingIcon = {
                        Icon(
                            Icons.Check,
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
                )
            }


            if (isStepTwoVisible) {
                TextField(
                    value = datumTwo,
                    onValueChange = { datumTwo = it },
                    label = { Text("Something in Two") },
                    info = { Text("Something else") },
                    leadingIcon = {
                        Icon(
                            Icons.Check,
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
                )

            }

            if (isStepThreeVisible) {
                TextField(
                    value = datumThree,
                    onValueChange = { datumThree = it },
                    label = { Text("Something in Three") },
                    info = { Text("Something else") },
                    leadingIcon = {
                        Icon(
                            Icons.Check,
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
                )
            }

            if (!isStepOneVisible && !isStepTwoVisible && !isStepThreeVisible) {
                Text("Success")
            }


            Spacer(modifier = Modifier.height(40.dp))

            ButtonCritical(
                onClick = {
                    if (isStepOneVisible) {
                        isStepOneVisible = false
                        isStepTwoVisible = true
                    } else if (isStepTwoVisible) {
                        isStepTwoVisible = false
                    } else if (isStepThreeVisible) {
                        isStepThreeVisible = false
                    }
                }
            ) {
                Text(text = "Click me")
            }


        }
    }
}
