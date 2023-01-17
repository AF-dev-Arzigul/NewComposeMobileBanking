package com.example.newcomposemobilebanking.screen.signUp

import com.example.newcomposemobilebanking.screen.signIn.SignInContract
import kotlinx.coroutines.flow.StateFlow

/*
 * Arzigul Nazarbaeva
 * 1/16/2023, Monday, 8:31 PM
*/


interface SignUpContract {

    interface SignUpView {
        val uiState: StateFlow<UiState>

        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val error: String,
        val openVerifyScreen: Boolean
    )

    sealed interface Intent {
        class CheckUser(
            val firstName: String,
            val lastName: String,
            val birthday: String,
            val gender: String,
            val phoneNumber: String,
            val password: String
        ) : Intent
    }

}
