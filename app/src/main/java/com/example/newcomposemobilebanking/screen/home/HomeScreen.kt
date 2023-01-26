package com.example.newcomposemobilebanking.screen.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.example.newcomposemobilebanking.R
import com.example.newcomposemobilebanking.ui.theme.NewComposeMobileBankingTheme
import com.example.newcomposemobilebanking.screen.home.HomeScreenContract.*
import com.example.newcomposemobilebanking.ui.theme.MyTypography
import com.example.newcomposemobilebanking.ui.theme.Theme


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

val list = arrayListOf(
    R.drawable.card,
    R.drawable.card,
    R.drawable.card
)

@Composable
fun HomeScreenContent(
    uiState: UiState,
    onEventDispatcher: (Intent) -> Unit
) {
    val (theme, setTheme) = remember { mutableStateOf("") }
    val (typography, setTypography) = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column {

            HomeActionBar()

            LazyRow(content = {
                items(list.size) {
                    CardItem()
                }
            })

            Row(modifier = Modifier.padding(20.dp)) {
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .size(65.dp)
                            .padding(bottom = 5.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 20.dp
                        ),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Icon(
                            Icons.Outlined.ShoppingCart,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxSize()
                        )
                    }
                    Text(
                        text = "Add Card",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .size(65.dp)
                            .padding(bottom = 5.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 20.dp
                        ),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.pay),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxSize()
                        )
                    }
                    Text(
                        text = "Pay",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .size(65.dp)
                            .padding(bottom = 5.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 20.dp
                        ),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Icon(
                            Icons.Outlined.Send,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxSize()
                        )
                    }
                    Text(
                        text = "Send",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }

                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Card(
                        modifier = Modifier
                            .size(65.dp)
                            .padding(bottom = 5.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 20.dp
                        ),
                        shape = RoundedCornerShape(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        )
                    ) {
                        Icon(
                            Icons.Outlined.MoreVert,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(15.dp)
                                .fillMaxSize()
                        )
                    }
                    Text(
                        text = "More",
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Button(onClick = {
                when (MyTypography.typography.value) {
                    "small" -> {
                        MyTypography.typography.value = "medium"
                    }
                    "medium" -> {
                        MyTypography.typography.value = "large"
                    }
                    else -> {
                        MyTypography.typography.value = "small"
                    }
                }
            }) {
                Text(text = MyTypography.typography.value)
            }

            Button(onClick = {
                when (Theme.themeMode.value) {
                    "dark" -> {
                        Theme.themeMode.value = "light"
                    }
                    "light" -> {
                        Theme.themeMode.value = "green"
                    }
                    "green" -> {
                        Theme.themeMode.value = "dark"
                    }
                    else -> {
                        Theme.themeMode.value = "dark"
                    }
                }
            }) {
                Text(text = Theme.themeMode.value)
            }
        }
    }
}

@Composable
fun ThemeDropDownMenu(
    theme: String,
    setTheme: (theme: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = true
        }) {
        Text(text = "Salom1")
        Text(text = "Salom2")
        Text(text = "Salom3")
    }
}

@Composable
fun TypographyDropDownMenu(
    size: String,
    setSize: (size: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        }) {

    }
}

@Composable
fun HomeActionBar() {
    Surface(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.background(Color(0xffEEEFF4))) {
            Text(
                modifier = Modifier
                    .padding(start = 20.dp, top = 20.dp, bottom = 20.dp)
                    .weight(1f),
                text = "My accounts",
                fontSize = 24.sp,
                color = Color.Black
            )
            Icon(
                Icons.Filled.Notifications,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(Alignment.CenterVertically),
                tint = Color.Black
            )
        }
    }
}

@Composable
fun CardItem() {
    Box(modifier = Modifier) {
        Image(
            modifier = Modifier
                .padding(10.dp)
                .width(300.dp)
                .height(180.dp),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.card),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    NewComposeMobileBankingTheme {
        HomeScreenContent(uiState = UiState(""), onEventDispatcher = {})
    }
}