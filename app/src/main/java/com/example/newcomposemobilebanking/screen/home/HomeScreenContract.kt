package com.example.newcomposemobilebanking.screen.home


import kotlinx.coroutines.flow.StateFlow


/*
 * Arzigul Nazarbaeva
 * 1/16/2023, Monday, 4:49 PM
*/

interface HomeScreenContract {

    interface HomeView {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(val error: String)

    sealed interface Intent {}

}