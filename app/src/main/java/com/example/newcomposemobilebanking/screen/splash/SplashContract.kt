package com.example.newcomposemobilebanking.screen.splash

import kotlinx.coroutines.flow.StateFlow

/*
 * Arzigul Nazarbaeva
 * 1/15/2023, Sunday, 3:17 PM
*/


interface SplashContract {

    interface SplashView {
        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val isFirstLaunch: Boolean,
        val isSignedIn: Boolean
    )

    sealed interface Intent{
        object OpenNext : Intent
    }

}