package com.example.newcomposemobilebanking.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color


private val GreenColorScheme = lightColorScheme(
    primary = Color(0xFF11B6A2),
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFF164D3F),
    onBackground = Color(0xFFFFFFFF),
)

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color(0xFF000000),
    onBackground = Color(0xFFFFFFFF),
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1B1B1D),

    /* Other default colors to override
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onSurface = Color(0xFF1C1B1F),
    */
)


object Theme {
    val themeMode = mutableStateOf("system")
}

object MyTypography {
    val typography = mutableStateOf("medium")
}

@Composable
fun NewComposeMobileBankingTheme(
    darkTheme: String = Theme.themeMode.value,
    // Dynamic color is available on Android 12+
    typography: String = MyTypography.typography.value,
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when (darkTheme) {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
        "dark" -> DarkColorScheme
        "light" -> LightColorScheme
        "green" -> GreenColorScheme
        else -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme
    }

//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
//            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
//        }
//    }

    val myTypography = when (typography) {
        "small" -> SmallTypography
        "medium" -> MediumTypography
        else -> LargeTypography
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = myTypography,
        content = content
    )

}