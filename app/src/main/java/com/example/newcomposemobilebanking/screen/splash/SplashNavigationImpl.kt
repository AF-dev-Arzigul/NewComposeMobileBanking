package com.example.newcomposemobilebanking.screen.splash

import com.example.newcomposemobilebanking.navigation.AppNavigationManager
import com.example.newcomposemobilebanking.screen.home.HomeScreen
import com.example.newcomposemobilebanking.screen.intro.IntroScreen
import com.example.newcomposemobilebanking.screen.signIn.SignInScreen
import javax.inject.Inject

class SplashNavigationImpl @Inject constructor(
    private val navigator: AppNavigationManager
) : SplashNavigation {

    override suspend fun navigateToIntroScreen() {
        navigator.replaceNavigation(IntroScreen())
    }

    override suspend fun navigateToHomeScreen() {
        navigator.replaceNavigation(HomeScreen())
    }

    override suspend fun navigateToSignInScreen() {
        navigator.replaceNavigation(SignInScreen())
    }
}