package com.example.newcomposemobilebanking.screen.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newcomposemobilebanking.model.local.LocalStorage
import com.example.newcomposemobilebanking.model.repository.AuthRepository
import com.example.newcomposemobilebanking.screen.signIn.SignInContract.*
import com.example.newcomposemobilebanking.util.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val localStorage: LocalStorage
) : SignInViewModel, ViewModel(), SignInView {
    override val uiState = MutableStateFlow(UiState("", false))
    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is Intent.CheckUser -> {
                authRepository.login(intent.phoneNumber, intent.password).onEach {
                    when (it) {
                        is ResultData.Error -> {
                            reduce { block -> block.copy(error = "Wrong phone number or password") }
                        }
                        is ResultData.Success -> {
                            localStorage.accessToken = it.data!!.token
                            reduce { block -> block.copy(openVerifyScreen = true) }
                        }
                    }
                }.launchIn(viewModelScope)
            }
        }
    }

    private fun reduce(block: (oldState: UiState) -> UiState) {
        val old = uiState.value
        uiState.value = block(old)
    }

}