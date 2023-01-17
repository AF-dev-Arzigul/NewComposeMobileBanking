package com.example.newcomposemobilebanking.screen.signUp


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newcomposemobilebanking.R
import com.example.newcomposemobilebanking.screen.signIn.SignInContract
import com.example.newcomposemobilebanking.screen.signIn.passwordEditText
import com.example.newcomposemobilebanking.screen.signIn.phoneEditText
import com.example.newcomposemobilebanking.screen.signUp.SignUpContract.*
import com.example.newcomposemobilebanking.screen.signUpVerify.SignUpVerifyScreen
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme
import com.example.newcomposemobilebanking.util.MaskVisualTransformation


/*
 * Arzigul Nazarbaeva
 * 1/11/2023, Wednesday, 11:58 AM
*/


class SignUpScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        NewComposeMobileBankingTheme {
            val viewModel: SignUpViewModelImpl = getViewModel()
            val uiState = viewModel.uiState.collectAsState().value
            SignUpScreenContent(uiState, viewModel::onEventDispatcher)
        }
    }
}

@Composable
fun SignUpScreenContent(
    uiState: UiState,
    onEventDispatcher: (Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var firstName by remember { mutableStateOf("") }
        var lastName by remember { mutableStateOf("") }
        var dateOfBirth by remember { mutableStateOf("") }
        var phoneCheck by remember { mutableStateOf("") }
        var passwordCheck by remember { mutableStateOf("") }
        var gender by remember { mutableStateOf("") }

        Column {
            Text(
                text = "Sign up",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Text(
                text = "First name",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            firstName = nameEditText()

            Text(
                text = "Last name",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            lastName = nameEditText()


            Text(
                text = "Birthday",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            dateOfBirth = birthdayEditText()

            Text(
                text = "Phone number",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            phoneCheck = phoneEditText()

            Text(
                text = "Password",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            passwordCheck = passwordEditText()

            Text(
                text = "Gender",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            gender = radioGroupUsage()

        }

        Button(
            onClick = {
                onEventDispatcher(Intent.CheckUser(firstName, lastName, dateOfBirth, gender, "+998$phoneCheck", passwordCheck))
            },
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(10.dp),
            enabled = phoneCheck.isNotEmpty() && passwordCheck.isNotEmpty() &&
                    firstName.isNotEmpty() && lastName.isNotEmpty() &&
                    dateOfBirth.isNotEmpty() && gender.isNotEmpty()
        ) {
            Text(text = "Sign In")
        }

        val navigator = LocalNavigator.currentOrThrow

        if (uiState.openVerifyScreen) {
            navigator.push(SignUpVerifyScreen("+998$phoneCheck"))
        }

    }
}

@Composable
fun radioGroupUsage(): String {
    val kinds = listOf("male", "female")
    val (selected, setSelected) = remember { mutableStateOf("") }
    Column {
        KindRadioGroup(
            mItems = kinds,
            selected,
            setSelected
        )
//        Text(
//            text = "Selected Option : $selected",
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth(),
//        )
    }
    return selected
}

@Composable
fun KindRadioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            mItems.forEach { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        modifier = Modifier.padding(start = 20.dp),
                        selected = selected == item,
                        onClick = {
                            setSelected(item)
                        },
                        enabled = true,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Magenta
                        )
                    )
                    Text(text = item, modifier = Modifier.padding(start = 8.dp, end = 20.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun nameEditText(): String {
    var name by remember { mutableStateOf("") }
    Box {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF4F4F4),
//                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            /*isError = false,*/
        )
    }
    return name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun birthdayEditText(): String {
    val dayMask = "##/##/####"
    var name by remember { mutableStateOf("") }
    Box {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF4F4F4),
//                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            placeholder = {
                Text(text = "12/25/2022", color = Color(0xffC5C5C5))
            },
            visualTransformation = MaskVisualTransformation(dayMask)
            /*isError = false,*/
        )
    }
    return name
}


@Preview
@Composable
fun SignUpPreview() {
    NewComposeMobileBankingTheme {
        SignUpScreenContent(UiState("", false)) {}
    }
}


