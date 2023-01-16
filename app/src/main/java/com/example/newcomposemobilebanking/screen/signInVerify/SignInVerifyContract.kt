package com.example.newcomposemobilebanking.screen.signInVerify


import kotlinx.coroutines.flow.StateFlow


/*
 * Arzigul Nazarbaeva
 * 1/16/2023, Monday, 3:38 PM
*/


interface SignInVerifyContract {

    interface SignInVerifyView {
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