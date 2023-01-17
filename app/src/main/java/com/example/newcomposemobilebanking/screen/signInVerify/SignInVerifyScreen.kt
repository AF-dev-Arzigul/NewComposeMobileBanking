package com.example.newcomposemobilebanking.screen.signInVerify


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newcomposemobilebanking.screen.home.HomeScreen
import com.example.newcomposemobilebanking.screen.signInVerify.SignInVerifyContract.*
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme
import kotlinx.coroutines.delay
import com.example.newcomposemobilebanking.util.TimerContent


/*
 * Arzigul Nazarbaeva
 * 1/11/2023, Wednesday, 11:58 AM
*/


class SignInVerifyScreen(private val phoneNumber: String) : AndroidScreen() {

    @Composable
    override fun Content() {
        NewComposeMobileBankingTheme {
            val viewModel: SignInVerifyViewModelImpl = getViewModel()
            val uiState = viewModel.uiState.collectAsState().value
            SignInVerifyScreenContent(phoneNumber, uiState, viewModel::onEventDispatcher)
        }
    }

}

@Composable
fun SignInVerifyScreenContent(
    phoneNumber: String,
    uiState: UiState,
    onEventDispatcher: (Intent) -> Unit
) {
    val (password, setPassword) = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
    ) {
        Column {
            AppBar(hasBackIcon = true)
            Text(
                text = "Код отправлен на номер\n$phoneNumber",
                color = Color(0xff808080),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                textAlign = TextAlign.Center
            )

            GroupVerifySmsCodeItem(password, setPassword)

            var time by remember { mutableStateOf(119) }
            if (time > 0) {
                LaunchedEffect(key1 = time, block = {
                    delay(1000)
                    time--
                })
            }
            if (time != 0) {
                TimerContent(time)
            } else {
                Text(
                    text = "Resend",
                    fontSize = 24.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
//                            there will be resend logic
                        },
                    textAlign = TextAlign.Center
                )
            }
        }
        val navigator = LocalNavigator.currentOrThrow
        Button(
            onClick = {
                onEventDispatcher(Intent.CheckCode(password))
            },
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp)
                .fillMaxWidth()
                .height(48.dp)
                .align(Alignment.BottomEnd),
            shape = RoundedCornerShape(10.dp),
            enabled = password.length == 6
        ) {
            Text(text = "Verify")
        }

        if (uiState.homeScreen) {
            navigator.replaceAll(HomeScreen())
        }

    }
}

@Composable
fun GroupVerifySmsCodeItem(
    password: String,
    setPassword: (password: String) -> Unit
) {
    var newPassword = ""
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(vertical = 50.dp)
        ) {
            newPassword += verifySmsCodeItem(ImeAction.Next)
            newPassword += verifySmsCodeItem(ImeAction.Next)
            newPassword += verifySmsCodeItem(ImeAction.Next)
            newPassword += verifySmsCodeItem(ImeAction.Next)
            newPassword += verifySmsCodeItem(ImeAction.Next)
            newPassword += verifySmsCodeItem(ImeAction.Done)
            setPassword(newPassword)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun verifySmsCodeItem(
    imeAction: ImeAction
): String {
    var password by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    Surface(modifier = Modifier.padding(5.dp)) {
        OutlinedTextField(
            modifier = Modifier
                .width(42.dp)
                .height(55.dp),
            value = password,
            onValueChange = {
                if (password.length < 1 && it.isDigitsOnly()) {
                    password = it
                    focusManager.moveFocus(FocusDirection.Next)
                    if (imeAction == ImeAction.Done) {
                        focusManager.clearFocus()
                    }
                } else {
                    password = it
                    focusManager.moveFocus(FocusDirection.Left)
                }
                Log.d("qqqqq", "item: $it")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                },
                onPrevious = {
                    focusManager.moveFocus(FocusDirection.Previous)
                },
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true,
            maxLines = 1
        )
    }
    return password
}


@Composable
fun AppBar(hasBackIcon: Boolean) {
    Box(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
    ) {
//        if (hasBackIcon) {
//            val navigator = LocalNavigator.currentOrThrow
//            Icon(
//                Icons.Filled.ArrowBack,
//                contentDescription = null,
//                modifier = Modifier
//                    .align(Alignment.CenterStart)
//                    .clickable { navigator.pop() }
//            )
//        }
        Text(
            text = "Confirmation",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 22.sp
        )
    }
}

@Preview
@Composable
fun SignInVerifyScreenPreview() {
    NewComposeMobileBankingTheme {
        SignInVerifyScreenContent("", UiState("", false)) {}
    }
}