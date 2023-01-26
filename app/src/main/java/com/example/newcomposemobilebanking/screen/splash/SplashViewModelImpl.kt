package com.example.newcomposemobilebanking.screen.splash


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newcomposemobilebanking.model.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import javax.inject.Inject


@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigation: SplashNavigationImpl
) : SplashViewModel, ViewModel() {

    override fun launch() {
        viewModelScope.launch {
            delay(1000)
            if (authRepository.isFirstLaunch()) {
               navigation.navigateToIntroScreen()
            } else if (authRepository.isSignedIn()) {
                navigation.navigateToHomeScreen()
            } else {
                navigation.navigateToSignInScreen()
            }
        }
    }

    override fun onEventDispatcher(intent: Nothing) {}

    override val container: Container<Nothing, Nothing>
        get() = TODO("Not yet implemented")

}
