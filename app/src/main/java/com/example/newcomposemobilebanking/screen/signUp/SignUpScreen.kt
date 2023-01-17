package com.example.newcomposemobilebanking.screen.signUp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newcomposemobilebanking.screen.signIn.PasswordEditText
import com.example.newcomposemobilebanking.screen.signIn.PhoneEditText
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
        val (firstName, setFirstName) = remember { mutableStateOf("") }
        val (lastName, setLastName) = remember { mutableStateOf("") }
        val (birthday, setBirthday) = remember { mutableStateOf("") }
        val (phone, setPhone) = remember { mutableStateOf("") }
        val (password, setPassword) = remember { mutableStateOf("") }
        val (gender, setGender) = remember { mutableStateOf("") }

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

            NameEditText(firstName, setFirstName)

            Text(
                text = "Last name",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            NameEditText(lastName, setLastName)

            Text(
                text = "Birthday",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            BirthdayEditText(birthday, setBirthday)

            Text(
                text = "Phone number",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            PhoneEditText(phone, setPhone)

            Text(
                text = "Password",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            PasswordEditText(password, setPassword)

            Text(
                text = "Gender",
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(start = 25.dp, end = 20.dp, bottom = 5.dp, top = 20.dp)
                    .fillMaxWidth(),
            )

            val kinds = listOf("male", "female")
            Column {
                GenderRadioGroup(mItems = kinds, gender, setGender)
            }

        }

        Button(
            onClick = {
                onEventDispatcher(Intent.CheckUser(firstName, lastName, birthday, gender, "+998$phone", password))
            },
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(10.dp),
            enabled = phone.isNotEmpty() && password.isNotEmpty() &&
                    firstName.isNotEmpty() && lastName.isNotEmpty() &&
                    birthday.isNotEmpty() && gender.isNotEmpty()
        ) {
            Text(text = "Sign In")
        }

        val navigator = LocalNavigator.currentOrThrow

        if (uiState.openVerifyScreen) {
            navigator.push(SignUpVerifyScreen("+998$phone"))
        }

    }
}


@Composable
fun GenderRadioGroup(
    mItems: List<String>,
    gender: String,
    setGender: (gender: String) -> Unit,
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
                        selected = gender == item,
                        onClick = {
                            setGender(item)
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
fun NameEditText(
    name: String,
    setName: (selected: String) -> Unit
) {
    Box {
        OutlinedTextField(
            value = name,
            onValueChange = { setName(it) },
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayEditText(
    birthday: String,
    setBirthday: (birthday: String) -> Unit
) {
    val dayMask = "##/##/####"
    Box {
        OutlinedTextField(
            value = birthday,
            onValueChange = { setBirthday(it) },
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
}


@Preview
@Composable
fun SignUpPreview() {
    NewComposeMobileBankingTheme {
        SignUpScreenContent(UiState("", false)) {}
    }
}


