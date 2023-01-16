package com.example.newcomposemobilebanking.screen.intro


import androidx.lifecycle.ViewModel
import com.example.newcomposemobilebanking.model.local.LocalStorage
import com.example.newcomposemobilebanking.screen.intro.IntroContract.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class IntroViewModelImpl @Inject constructor(
    private val localRepo: LocalStorage
) : IntroViewModel, SplashView, ViewModel() {
    override val uiState = MutableStateFlow(UiState(true))

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is Intent.FirstLaunch -> {
                localRepo.isFirstLaunch = intent.isFirstLaunch
                reduce { it.copy(next = true) }
            }
        }
    }

    private fun reduce(block: (oldState: UiState) -> UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }

}