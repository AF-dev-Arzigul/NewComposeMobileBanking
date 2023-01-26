package com.example.newcomposemobilebanking.screen.splash


/*
 * Arzigul Nazarbaeva
 * 1/20/2023, Friday, 4:43 PM
*/


interface SplashNavigation {
    suspend fun navigateToIntroScreen()
    suspend fun navigateToHomeScreen()
    suspend fun navigateToSignInScreen()
}