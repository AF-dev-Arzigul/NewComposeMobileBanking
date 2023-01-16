package com.example.newcomposemobilebanking.screen.intro

import kotlinx.coroutines.flow.StateFlow

/*
 * Arzigul Nazarbaeva
 * 1/16/2023, Monday, 12:41 PM
*/


interface IntroContract {

    interface SplashView {
        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val next: Boolean
    )

    sealed interface Intent{
        class FirstLaunch(val isFirstLaunch: Boolean) : Intent
    }

}