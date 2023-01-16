package com.example.newcomposemobilebanking.screen.home


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.newcomposemobilebanking.screen.home.HomeScreenContract.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeViewModelImpl @Inject constructor() : HomeViewModel, ViewModel(), HomeView {
    override val uiState = MutableStateFlow(UiState(""))

    override fun onEventDispatcher(intent: Intent) {
//        when(intent){ }

    }


}