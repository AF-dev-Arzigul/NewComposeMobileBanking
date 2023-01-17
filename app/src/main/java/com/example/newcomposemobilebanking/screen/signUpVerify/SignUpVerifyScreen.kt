package com.example.newcomposemobilebanking.screen.signUpVerify


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newcomposemobilebanking.screen.home.HomeScreen
import com.example.newcomposemobilebanking.screen.signInVerify.AppBar
import com.example.newcomposemobilebanking.screen.signInVerify.groupVerifySmsCodeItem
import com.example.newcomposemobilebanking.screen.signUpVerify.SignUpVerifyContract.*
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme
import com.example.newcomposemobilebanking.util.TimerContent
import kotlinx.coroutines.delay


/*
 * Arzigul Nazarbaeva
 * 1/11/2023, Wednesday, 11:58 AM
*/


class SignUpVerifyScreen(private val phoneNumber: String) : AndroidScreen() {

    @Composable
    override fun Content() {
        NewComposeMobileBankingTheme {
            val viewModel: SignUpVerifyViewModelImpl = getViewModel()
            val uiState = viewModel.uiState.collectAsState().value
            SignUpVerifyScreenContent(phoneNumber, uiState, viewModel::onEventDispatcher)
        }
    }

}

@Composable
fun SignUpVerifyScreenContent(
    phoneNumber: String,
    uiState: UiState,
    onEventDispatcher: (Intent) -> Unit
){
    var password by remember {
        mutableStateOf("")
    }
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
            password = groupVerifySmsCodeItem()
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
                            Log.d("qqqqq", "text clicked")
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

@Preview
@Composable
fun SignUpVerifyScreenPreview() {
    NewComposeMobileBankingTheme {
        SignUpVerifyScreenContent("", UiState("", false)) {}
    }
}