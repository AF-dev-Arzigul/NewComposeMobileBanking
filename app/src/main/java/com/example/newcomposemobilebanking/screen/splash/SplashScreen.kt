package com.example.newcomposemobilebanking.screen.splash


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme


@SuppressLint("CustomSplashScreen")
class SplashScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        NewComposeMobileBankingTheme {
            val viewModel: SplashViewModelImpl = getViewModel()
            viewModel.launch()
            SplashScreenContent()
        }
    }

}

@Composable
fun SplashScreenContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff102060))
    ) {
        Box(modifier = Modifier.align(Alignment.Center)) {
            GitaIcon(Color.White)
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
    NewComposeMobileBankingTheme {}
}