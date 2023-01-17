package com.example.newcomposemobilebanking.screen.signUpVerify


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newcomposemobilebanking.model.local.LocalStorage
import com.example.newcomposemobilebanking.model.remote.request.SignUpVerifyRequest
import com.example.newcomposemobilebanking.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.newcomposemobilebanking.screen.signUpVerify.SignUpVerifyContract.*
import com.example.newcomposemobilebanking.util.ResultData
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class SignUpVerifyViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val localStorage: LocalStorage
) : SignUpVerifyViewModel, ViewModel(), SignUpVerifyView {
    override val uiState = MutableStateFlow(UiState("", false))

    override fun onEventDispatcher(intent: Intent) {
        when (intent) {
            is Intent.CheckCode -> {
                val code = intent.code
                authRepository.verifySignUp(SignUpVerifyRequest(localStorage.accessToken, code))
                    .onEach {
                        when (it) {
                            is ResultData.Error -> {
                                reduce { error -> error.copy(error = "Something went wrong") }
                            }
                            is ResultData.Success -> {
                                localStorage.isSignedIn = true
                                localStorage.accessToken = it.data.accessToken
                                localStorage.refreshToken = it.data.refreshToken
                                Log.d("qqqqq", "vm keldi")
                                reduce { success -> success.copy(homeScreen = true) }
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