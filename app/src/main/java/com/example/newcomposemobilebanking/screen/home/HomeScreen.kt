package com.example.newcomposemobilebanking.screen.home


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme
import com.example.newcomposemobilebanking.screen.home.HomeScreenContract.*


/*
 * Arzigul Nazarbaeva
 * 1/11/2023, Wednesday, 11:57 AM
*/


class HomeScreen : AndroidScreen() {

    @Composable
    override fun Content() {
        NewComposeMobileBankingTheme {
            val viewModel: HomeViewModelImpl = getViewModel()
            val uiState = viewModel.uiState.collectAsState().value
            HomeScreenContent(uiState, viewModel::onEventDispatcher)
        }
    }
}

@Composable
fun HomeScreenContent(
    uiState: UiState,
    onEventDispatcher: (Intent) -> Unit
) {

}

@Composable
fun HomeActionBar() {

}

@Preview
@Composable
fun HomeScreenPreview() {
    NewComposeMobileBankingTheme {
        HomeScreenContent(uiState = UiState(""), onEventDispatcher = {})
    }
}