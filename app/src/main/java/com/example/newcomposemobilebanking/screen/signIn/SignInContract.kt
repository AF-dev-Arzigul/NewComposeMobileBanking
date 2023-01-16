package com.example.newcomposemobilebanking.screen.signIn


import kotlinx.coroutines.flow.StateFlow

/*
 * Arzigul Nazarbaeva
 * 1/16/2023, Monday, 2:43 PM
*/

interface SignInContract {
    interface SignInView {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val error: String,
        val openVerifyScreen: Boolean
    )

    sealed interface Intent {
        class CheckUser(val phoneNumber: String, val password: String) : Intent
    }
}