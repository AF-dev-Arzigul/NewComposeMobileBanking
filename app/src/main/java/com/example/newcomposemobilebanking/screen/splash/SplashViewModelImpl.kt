package com.example.newcomposemobilebanking.screen.splash


import androidx.lifecycle.ViewModel
import com.example.newcomposemobilebanking.model.repository.AuthRepository
import com.example.newcomposemobilebanking.screen.splash.SplashContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    authRepository: AuthRepository
) : SplashViewModel, ViewModel(), SplashView {

    override val uiState = MutableStateFlow(UiState(isFirstLaunch = true, isSignedIn = true))

    init {
        reduce { it.copy(isFirstLaunch = authRepository.isFirstLaunch()) }
        reduce { it.copy(isSignedIn = authRepository.isSignedIn()) }
    }

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is Intent.OpenNext -> {}
        }
    }

    private fun reduce(block: (oldState: UiState) -> UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }

}
