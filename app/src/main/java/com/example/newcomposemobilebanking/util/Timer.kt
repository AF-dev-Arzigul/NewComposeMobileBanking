package com.example.newcomposemobilebanking.util


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme


/*
 * Arzigul Nazarbaeva
 * 1/14/2023, Saturday, 1:06 PM
*/

@Composable
fun TimerContent(time: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text =
            if (time > 69) "01:${time - 60}"
            else if (time in 61..69) "01:0${time - 60}"
            else if (time < 10) "00:0$time"
            else if (time == 60) "01:00"
            else "00:$time",
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun TimerPreview() {
    NewComposeMobileBankingTheme() {
        TimerContent(59)
    }
}
