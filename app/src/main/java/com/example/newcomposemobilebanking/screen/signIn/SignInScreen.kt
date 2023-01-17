package com.example.newcomposemobilebanking.screen.signIn


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newcomposemobilebanking.R
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme
import com.example.newcomposemobilebanking.util.MaskVisualTransformation
import com.example.newcomposemobilebanking.screen.signIn.SignInContract.*
import com.example.newcomposemobilebanking.screen.signInVerify.SignInVerifyScreen
import com.example.newcomposemobilebanking.screen.signUp.SignUpScreen


/*
 * Arzigul Nazarbaeva
 * 1/11/2023, Wednesday, 11:55 AM
*/


class SignInScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        NewComposeMobileBankingTheme {
            val viewModel: SignInViewModelImpl = getViewModel()
            val uiState = viewModel.uiState.collectAsState().value
            SignInScreenContent(uiState, viewModel::onEventDispatcher)
        }
    }

}

@Composable
fun SignInScreenContent(
    uiState: UiState,
    onEventDispatcher: (Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val navigator = LocalNavigator.currentOrThrow

        val (phone, setPhone) = remember { mutableStateOf("") }
        val (password, setpassword) = remember { mutableStateOf("") }

        Column {
            Text(
                text = "Sign in",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )

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

            PasswordEditText(password, setpassword)

            Text(
                modifier = Modifier.padding(20.dp),
                text = "Forget password?",
                color = Color.Blue,
            )

            Row {
                Text(
                    modifier = Modifier.padding(start = 20.dp, top = 20.dp),
                    text = "If you don't have an account, ",
                    color = Color.Black,
                )

                Text(
                    modifier = Modifier
                        .padding(end = 20.dp, top = 20.dp)
                        .clickable {
                            navigator.push(SignUpScreen())
                        },
                    text = "register",
                    color = Color.Blue,
                )

            }

        }

        Button(
            onClick = {
                onEventDispatcher(Intent.CheckUser("+998$phone", password))
            },
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(10.dp),
            enabled = phone.isNotEmpty() && password.isNotEmpty()
        ) {
            Text(text = "Sign In")
        }

        if (uiState.openVerifyScreen) {
            navigator.push(SignInVerifyScreen("+998$phone"))
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneEditText(
    phone: String,
    setPhone: (phone: String) -> Unit
) {
    val phoneMask = " (##) - ### - ## - ##"
    Box {
        OutlinedTextField(
            value = phone,
            onValueChange = {
                /*if (phone.length < 9)*/
                setPhone(it)
            },
            leadingIcon = {
                Text(text = "+998", color = Color.Black)
            },
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
            /*.onKeyEvent {
            }*/,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color(0xFFF4F4F4),
//                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            visualTransformation = MaskVisualTransformation(phoneMask)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordEditText(
    password: String,
    setPassword: (password: String) -> Unit
) {
    var toggle by remember { mutableStateOf(false) }
    Box {
        OutlinedTextField(
            value = password,
            onValueChange = { setPassword(it) },
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
            visualTransformation = if (toggle) PasswordVisualTransformation() else VisualTransformation.None,
            /*isError = false,*/
            trailingIcon = {
                val icon = if (!toggle) {
                    R.drawable.ic_baseline_visibility_24
                } else {
                    R.drawable.ic_baseline_visibility_off_24
                }
                IconButton(
                    onClick = { toggle = !toggle },
                ) {
                    Icon(painter = painterResource(id = icon), contentDescription = null)
                }
            }
        )
    }
}

@Preview
@Composable
fun SignInPreview() {
    NewComposeMobileBankingTheme {
        SignInScreenContent(UiState("", false)) {}
    }
}
