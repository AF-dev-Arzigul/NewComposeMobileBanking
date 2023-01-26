package com.example.newcomposemobilebanking.navigation

import cafe.adriel.voyager.core.screen.Screen

interface NavigationHandler {
    suspend fun back()
    suspend fun navigationTo(screen: Screen)
    suspend fun replaceNavigation(screen: Screen)
}

