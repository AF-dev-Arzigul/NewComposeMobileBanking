package com.example.newcomposemobilebanking.screen.signUpVerify

import kotlinx.coroutines.flow.StateFlow

/*
 * Arzigul Nazarbaeva
 * 1/16/2023, Monday, 8:29 PM
*/


interface SignUpVerifyContract {

    interface SignUpVerifyView {
        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val error:String,
        val homeScreen: Boolean
    )

    sealed interface Intent {
        class CheckCode(val code:String): Intent
    }

}