package com.example.newcomposemobilebanking.screen.splash


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.newcomposemobilebanking.screen.splash.SplashContract.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newcomposemobilebanking.R
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.newcomposemobilebanking.screen.home.HomeScreen
import com.example.newcomposemobilebanking.screen.intro.IntroScreen
import com.example.newcomposemobilebanking.screen.signIn.SignInScreen
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme
import kotlinx.coroutines.delay


@SuppressLint("CustomSplashScreen")
class SplashScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        NewComposeMobileBankingTheme {
            val viewModel: SplashViewModelImpl = getViewModel()
            val uiState = viewModel.uiState.collectAsState().value
            SplashScreenContent(uiState, viewModel::onEventDispatcher)
        }
    }

}

@Composable
fun SplashScreenContent(
    uiState: UiState,
    eventDispatcher: (Intent) -> Unit
) {
    val navigator = LocalNavigator.currentOrThrow
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff102060))
    ) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            GitaIcon(Color.White)

            LaunchedEffect(key1 = "", block = {
                delay(1000)
                if (uiState.isSignedIn) {
                    Log.d("qqqqq", "2")
                    navigator.replace(HomeScreen())
                } else if (uiState.isFirstLaunch) {
                    Log.d("qqqqq", "1")
                    navigator.replace(IntroScreen())
                } else {
                    Log.d("qqqqq", "3")
                    navigator.replace(SignInScreen())
                }
            })
        }
    }
}

@Composable
fun GitaIcon(color: Color) {
    Box {
        Row {
            Image(
                painter = painterResource(id = R.drawable.splash_icon),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(50.dp)
            )
            Text(
                text = "GITA BANK",
                color = color,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    NewComposeMobileBankingTheme {
        SplashScreenContent(UiState(isFirstLaunch = false, isSignedIn = false)) {}
    }
}